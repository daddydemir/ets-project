package com.example.etsproject.service.concrete;

import com.example.etsproject.core.business.BusinessRules;
import com.example.etsproject.entity.Reservation;
import com.example.etsproject.repository.ReservationRepository;
import com.example.etsproject.service.abstracts.ReservationService;
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
        if (result == null){
            return new ErrorDataResult<>("Rezervasyon yaptırmamışsınız.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<Reservation>> getByHotelId(int hotelId) {
        var result = reservationRepository.findByHotelId(hotelId);
        if (result == null){
            return new ErrorDataResult<>("Rezervasyon bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Reservation reservation) {
        var rule = BusinessRules.run(validation(reservation.getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        reservationRepository.save(reservation);
        return new SuccessResult("Rezervasyon kaydınız başarıyla oluşturuldu.");
    }

    @Override
    public Result delete(int id) {
        var rule = BusinessRules.run(validation(getById(id).getData().getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        reservationRepository.delete(getById(id).getData());
        return new SuccessResult("Rezervasyonunuz iptal edildi.");
    }
}
