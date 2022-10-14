package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Reservation;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface ReservationService {

    DataResult<Reservation> getById(int id);

    DataResult<List<Reservation>> getByCustomerId(int customerId);

    DataResult<List<Reservation>> getByHotelId(int hotelId);

    Result add(Reservation reservation);

    Result delete(int id);
}
