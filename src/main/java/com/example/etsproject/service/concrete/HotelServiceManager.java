package com.example.etsproject.service.concrete;


import com.example.etsproject.dto.HotelDto;
import com.example.etsproject.entity.Hotel;
import com.example.etsproject.repository.HotelRepository;
import com.example.etsproject.service.abstracts.HotelService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HotelServiceManager implements HotelService {

    private final HotelRepository hotelRepository;
    private final RoomServiceManager roomServiceManager;
    private final AddressServiceManager addressServiceManager;

    @Override
    public DataResult<Hotel> getById(int id) {
        var result = hotelRepository.findById(id);
        if (result == null) {
            return new ErrorDataResult<>("Otel bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<Hotel> getByReservationId(int resId) {
        var result = hotelRepository.findByReservationsId(resId);
        if (result == null) {
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

    @Override
    public DataResult<Set<Hotel>> search(HotelDto hotelDto) {
        Set<Hotel> hotels = new HashSet<>();
        Set<Hotel> room_hotels = new HashSet<>();

        Set<Hotel> last = new HashSet<>();

        var address = addressServiceManager.getAllAddressByCityId(hotelDto.getCity());
        if (!address.isSuccess()) {
            return new ErrorDataResult<>("Aradığınız bölgede boş otel bulunamadı.1");
        }


        var room = roomServiceManager.search(hotelDto.getAdult(), hotelDto.getChild());
        if (!room.isSuccess()) {
            return new ErrorDataResult<>("Aradığınız bölgede boş otel bulunamadı.2");
        }



        var hotel = hotelRepository.search();
        if (hotel.isEmpty()) {
            return new ErrorDataResult<>("Aradığınız bölgede boş otel bulunamadı.3");
        }

        for (Hotel value : hotel) {
            for (int a = 0; a < address.getData().size(); a++) {
                if (value.getAddressId() == address.getData().get(a).getId()) {
                    hotels.add(value);
                }
            }
        }

        for (int i = 0; i < room.getData().size(); i++) {
            for (Hotel value : hotel) {

                // aynı oteli birden fazla kez eklememk için hashset yaspısnı kullan...
                if (value.getId() == room.getData().get(i).getHotelId()) {
                    room_hotels.add(value);
                }
            }
        }


        for(Hotel hroom: room_hotels){
            for (Hotel aroom: hotels){
                if(hroom.getId() == aroom.getId()){
                    last.add(hroom);
                }
            }
        }

        if (last.isEmpty()) {
            return new ErrorDataResult<>("Aradığınız bölgede boş otel bulunamadı.4");
        }

        return new SuccessDataResult<>(last,"");
    }
}
