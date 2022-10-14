package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Hotel;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

public interface HotelService {

    DataResult<Hotel> getById(int id);

    DataResult<Hotel> getByReservationId(int resId);

    Result add(Hotel hotel);

    Result delete(int id);

    Result update(Hotel hotel);
}
