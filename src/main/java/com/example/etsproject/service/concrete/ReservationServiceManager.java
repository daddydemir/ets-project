package com.example.etsproject.service.concrete;

import com.example.etsproject.core.business.BusinessRules;
import com.example.etsproject.dto.ReservationDto;
import com.example.etsproject.entity.Reservation;
import com.example.etsproject.entity.Room;
import com.example.etsproject.repository.ReservationRepository;
import com.example.etsproject.service.abstracts.HotelService;
import com.example.etsproject.service.abstracts.ReservationService;
import com.example.etsproject.service.abstracts.RoomService;
import com.example.etsproject.service.abstracts.TokenValidationService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceManager implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final TokenValidationService tokenValidationService;
    private final HotelService hotelService;
    private RoomService roomService;

    private Result validation(int customerId){
        var result = tokenValidationService.customerIdAndTokenEmailVerification(customerId);
        if (!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<Reservation> getById(int id) {
        var result = reservationRepository.findById(id);
        if (result == null){
            return new ErrorDataResult<>("Rezervasyon bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<Reservation>> getByCustomerId(int customerId) {
        var rule = BusinessRules.run(validation(customerId));
        if (rule != null){
            return new ErrorDataResult<>(null,rule.getMessage());
        }
        var result = reservationRepository.findByCustomerId(customerId);
        if (result.isEmpty()){
            return new ErrorDataResult<>("Rezervasyon yaptırmamışsınız.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<Reservation>> getByHotelId(int hotelId) {
        var result = reservationRepository.findByHotelId(hotelId);
        if (result.isEmpty()){
            return new ErrorDataResult<>("Rezervasyon bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(ReservationDto reservationDto) {
        var rule = BusinessRules.run(validation(reservationDto.getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        // todo boş oda sayısı 1 eksildi
        var reservation = new Reservation();
        reservation.setCustomerId(reservationDto.getCustomerId());
        reservation.setEndDate(reservationDto.getEndDate());
        reservation.setHotelId(reservationDto.getHotelId());
        reservation.setStartDate(reservationDto.getStartDate());

        var hotel_result = hotelService.getById(reservation.getHotelId());
        if (hotel_result == null) {
            return new ErrorResult("Otel bulunamadı.");
        }
        hotel_result.getData().setEmptyRoomSize(hotel_result.getData().getEmptyRoomSize() - 1);
        hotelService.update(hotel_result.getData());
        List<Room> liste = hotel_result.getData().getRoom();
        int total_day = reservationDto.getEndDate().getDate() - reservationDto.getStartDate().getDate();
        reservation.setPrice(liste.get(0).getPrice() * total_day);
        reservationRepository.save(reservation);
        return new SuccessResult("Rezervasyon kaydınız başarıyla oluşturuldu.");
    }

    @Override
    public Result delete(int id) {
        var rule = BusinessRules.run(validation(getById(id).getData().getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        var reservation = getById(id);

        var hotel_result = hotelService.getById(reservation.getData().getHotelId());
        if (hotel_result == null) {
            return new ErrorResult("Otel bulunamadı.");
        }
        hotel_result.getData().setEmptyRoomSize(hotel_result.getData().getEmptyRoomSize() + 1);
        hotelService.update(hotel_result.getData());

        reservationRepository.delete(reservation.getData());
        return new SuccessResult("Rezervasyonunuz iptal edildi.");
    }
}
