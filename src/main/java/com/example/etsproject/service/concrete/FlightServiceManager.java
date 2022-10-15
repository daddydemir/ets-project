package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Flight;
import com.example.etsproject.repository.FlightRepository;
import com.example.etsproject.service.abstracts.FlightService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceManager implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public DataResult<List<Flight>> findByCustomQuery(String from, String to, Date departureTime, Date t, int eBusiness, int eEconomy) {

        Date next = new Date(departureTime.getTime());
        next.setDate(next.getDate() +1);
        System.out.println(
                "date-1:" + departureTime
                +"\ndate-2:" + next
        );
        var result = flightRepository.findFlightByDeparturePointAndDestinationAndDepartureTimeAndEmptyBusinessAndEmptyEconomyNamedParams(from,to,departureTime,next,eBusiness,eEconomy);
        if (result == null){
            return new ErrorDataResult<>("Uçuş bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<Flight>> getAll() {
        // bu nerede kullanılacaksa artık yazmış bululndum.
        return new SuccessDataResult<>(flightRepository.findAll());
    }

    @Override
    public DataResult<List<Flight>> getAllByPlaneId(int id) {
        var result = flightRepository.findAllByPlaneId(id);
        if(result.isEmpty()){
            return new ErrorDataResult<>("Bu firmaya ait uçuş bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<Flight> getById(int id) {
        var result = flightRepository.findById(id);
        if(result == null){
            return new ErrorDataResult<>("Uçuş bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Flight flight) {
        flightRepository.save(flight);
        return new SuccessResult("Uçuş başarıyla eklendi.");
    }

    @Override
    public Result update(Flight flight) {
        flightRepository.save(flight);
        return new SuccessResult("Uçuş güncellendi.");
    }

    @Override
    public Result delete(int id) {
        flightRepository.delete(getById(id).getData());
        return new SuccessResult("Uçuş iptal edildi.");
        // TODO: 05/10/2022 uçuşu iptal edilen müşterilerin hesabına para aktarılmalı ve bilgilendirilmeli.
    }
}
