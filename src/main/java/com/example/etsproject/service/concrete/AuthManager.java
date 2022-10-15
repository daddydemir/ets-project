package com.example.etsproject.service.concrete;

import com.example.etsproject.dto.CustomerDto;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.entity.Customer;
import com.example.etsproject.repository.CustomerRepository;
import com.example.etsproject.service.abstracts.CustomerService;
import com.example.etsproject.service.abstracts.AuthService;
import com.example.etsproject.utils.ErrorResult;
import com.example.etsproject.utils.Result;
import com.example.etsproject.utils.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Result registerCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhone(customerDto.getPhone());
        System.out.println(customerRepository.findCustomerById(2).getName());
        System.out.println(customerService.findById(2).getData().getSurname());
        customerRepository.save(customer);
        return new SuccessResult("Kullanıcı kaydı başarılı.");
    }

    @Override
    public Result login(LoginDto loginDto) {
        var userToCheck = customerService.getByEmail( loginDto.getEmail());
        if(!userToCheck.isSuccess()){
            return new ErrorResult("Email adresi yanlış.");
        }
        if(!passwordEncoder.matches(loginDto.getPassword(), userToCheck.getData().getPassword())){
            return new ErrorResult("Parola yanlış.");
        }
        return new SuccessResult();
    }

    @Override
    public Result changePassword() {
        return null;
    }
}
