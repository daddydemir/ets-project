package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Address;
import com.example.etsproject.repository.AddressRepository;
import com.example.etsproject.service.abstracts.AddressService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceManager implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public DataResult<Address> getAddressById(int id) {
        var result = addressRepository.findById(id);
        if (result == null){
            return new ErrorDataResult<>("Hiç kayıt bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public DataResult<List<Address>> getAllAddressByCityId(String city) {
        var result = addressRepository.findAllByCity(city);
        if (result == null){
            return new ErrorDataResult<>("Bu şehire ait hiç kayıt bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Address address) {
        addressRepository.save(address);
        return new SuccessResult("Adres başarıyla eklendi.");
    }

    @Override
    public Result update(Address address) {
        addressRepository.save(address);
        return new SuccessResult("Adres başarıyla güncellendi.");
    }
}
