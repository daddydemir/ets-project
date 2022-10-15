package com.example.etsproject.service.concrete;

import com.example.etsproject.core.business.BusinessRules;
import com.example.etsproject.dto.PersonDto;
import com.example.etsproject.dto.TicketDto;
import com.example.etsproject.entity.Flight;
import com.example.etsproject.entity.Person;
import com.example.etsproject.entity.PlaneTicket;
import com.example.etsproject.repository.FlightRepository;
import com.example.etsproject.repository.PersonRepository;
import com.example.etsproject.repository.PlaneTicketRepository;
import com.example.etsproject.service.abstracts.PlaneTicketService;
import com.example.etsproject.service.abstracts.TokenValidationService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneTicketServiceManager implements PlaneTicketService {

    private final PlaneTicketRepository planeTicketRepository;
    private final FlightRepository flightRepository;
    private final PersonRepository personRepository;
    private final TokenValidationService tokenValidationService;

    private Result validation(int customerId){
        var result = tokenValidationService.customerIdAndTokenEmailVerification(customerId);
        if (!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<PlaneTicket> findById(int id) {
        var result = planeTicketRepository.findById(id);
        if (result == null){
            return new ErrorDataResult<>("Bilet bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<PlaneTicket>> findByCustomerId(int id) {
        var rule = BusinessRules.run(validation(id));
        if (rule != null){
            return new ErrorDataResult<>(null, rule.getMessage());
        }
        var result = planeTicketRepository.findAllByCustomerId(id);
        if (result.isEmpty()){
            return new ErrorDataResult<>("Hiç biletiniz yok.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<PlaneTicket>> findByPersonId(int id) {
        var result = planeTicketRepository.findAllByPersonId(id);
        if (result.isEmpty()){
            return new ErrorDataResult<>("Kişiye ait hiç bilet bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<PlaneTicket>> findByFlightId(int id) {
        var result = planeTicketRepository.findAllByFlightId(id);
        if (result.isEmpty()){
            return new ErrorDataResult<>("Uçuşa ait bilet bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(TicketDto ticket) {
        PlaneTicket temp = new PlaneTicket();

        Person person = new Person();
        Flight data = flightRepository.findById(ticket.getFlightId());
        // pticketdto.person -> person
        person.setEmail(ticket.getPerson().getEmail());
        person.setGender((ticket.getPerson().getGender()));
        person.setName(ticket.getPerson().getName());
        person.setSurname(ticket.getPerson().getSurname());
        person.setIdentityNo(ticket.getPerson().getIdentityNo());

        var person_result = personRepository.save(person);
        temp.setPersonId(person_result.getId());
        if(data == null){
            return new ErrorResult("Uçuş bulunamadı.");
        }
        temp.setCustomerId(ticket.getCustomerId());
        temp.setFlightId(ticket.getFlightId());

        if(ticket.getSeat().equals("BUSINESS")){
            temp.setSeatId(1);
            temp.setPrice(data.getPriceBusiness());
        }else{
            temp.setPrice(data.getPriceEconomy());
            temp.setSeatId(2);
        }
        var rule = BusinessRules.run(validation(ticket.getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        // TODO: 05/10/2022 bilet satın alındığında uçaktaki koltuk kapasitesi eksilmeli.


        planeTicketRepository.save(temp);
        if(ticket.getSeat().equals("BUSINESS")){
            data.setEmptyBusiness(data.getEmptyBusiness() - 1);
        }else{
            data.setEmptyEconomy(data.getEmptyEconomy() - 1);
        }
        flightRepository.save(data);
        return new SuccessResult("Bileti satın aldınız.");
    }

    @Override
    public Result delete(int id) {
        var rule = BusinessRules.run(validation(findById(id).getData().getCustomerId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        // TODO: 05/10/2022  bilet iptal edildiğinde uçaktaki koltuk kapasitesi artırılmalı.
        PlaneTicket ticket = findById(id).getData();
        Flight f = flightRepository.findById(ticket.getFlightId());
        if(ticket.getSeatId() == 1){
            // business
            f.setEmptyBusiness(f.getEmptyBusiness() + 1);
        }else{
            //economy
            f.setEmptyEconomy(f.getEmptyEconomy() + 1);
        }
        flightRepository.save(f);
        planeTicketRepository.delete(ticket);
        return new SuccessResult("Biletiniz iptal edildi. 3 iş günü içersinde bilet ücreti hesabınıza aktarılacaktır.");
    }
}
