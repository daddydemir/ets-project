package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Flight;
import com.example.etsproject.entity.PlaneTicket;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightService {


    DataResult<List<Flight>> findByCustomQuery(String from, String to, Date departureTime, Date date, int eBusiness, int eEconomy);

    DataResult<List<Flight>> getAll();

    DataResult<List<Flight>> getAllByPlaneId(int id);

    DataResult<Flight> getById(int id);

    Result add(Flight flight);

    Result update(Flight flight);

    Result delete(int id);

    DataResult<List<Flight>> getir();
}
