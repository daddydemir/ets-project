package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Seat;
import com.example.etsproject.utils.DataResult;

import java.util.List;

public interface SeatService {

    DataResult<List<Seat>> findAll();
}
