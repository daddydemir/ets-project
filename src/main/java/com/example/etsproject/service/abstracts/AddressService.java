package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Address;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface AddressService {

    DataResult<Address> getAddressById(int id);

    DataResult<List<Address>> getAllAddressByCityId(String city);

    Result add(Address address);

    Result update(Address address);
}
