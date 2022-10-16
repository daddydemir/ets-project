package com.example.etsproject.service.abstracts;

import com.example.etsproject.dto.HotelDto;
import com.example.etsproject.entity.Hotel;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;
import java.util.Set;

public interface HotelService {

    DataResult<Hotel> getById(int id);

    DataResult<Hotel> getByReservationId(int resId);

    Result add(Hotel hotel);

    Result delete(int id);

    Result update(Hotel hotel);

    DataResult<Set<Hotel>> search(HotelDto hotelDto);
}
