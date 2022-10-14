package com.example.etsproject.service.concrete;


import com.example.etsproject.entity.Hotel;
import com.example.etsproject.repository.HotelRepository;
import com.example.etsproject.service.abstracts.HotelService;
import com.example.etsproject.service.abstracts.TokenValidationService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceManager implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public DataResult<Hotel> getById(int id) {
        var result = hotelRepository.findById(id);
        if (result == null){
            return new ErrorDataResult<>("Otel bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<Hotel> getByReservationId(int resId) {
        var result = hotelRepository.findByReservationsId(resId);
        if (result == null){
            return new ErrorDataResult<>("Otel bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Hotel hotel) {
        hotelRepository.save(hotel);
        return new SuccessResult("Otel başarıyla eklendi.");
    }

    @Override
    public Result delete(int id) {
        // TODO: 11/10/2022 null check ?
        hotelRepository.delete(getById(id).getData());
        return new SuccessResult("Otel silindi.");
    }

    @Override
    public Result update(Hotel hotel) {
        hotelRepository.save(hotel);
        return new SuccessResult("Otel bilgileri güncellendi.");
    }
}
