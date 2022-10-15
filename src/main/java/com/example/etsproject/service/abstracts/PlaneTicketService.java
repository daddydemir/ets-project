package com.example.etsproject.service.abstracts;

import com.example.etsproject.dto.TicketDto;
import com.example.etsproject.entity.PlaneTicket;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface PlaneTicketService {

    DataResult<PlaneTicket> findById(int id);

    DataResult<List<PlaneTicket>> findByCustomerId(int id);

    DataResult<List<PlaneTicket>> findByPersonId(int id);

    DataResult<List<PlaneTicket>> findByFlightId(int id);
    
    Result add(TicketDto ticket);

    Result delete(int id);
}
