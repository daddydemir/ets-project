package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Seat;
import com.example.etsproject.repository.SeatRepository;
import com.example.etsproject.service.abstracts.SeatService;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceManager implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public DataResult<List<Seat>> findAll() {
        return new SuccessDataResult<>(seatRepository.findAll());
    }
}
