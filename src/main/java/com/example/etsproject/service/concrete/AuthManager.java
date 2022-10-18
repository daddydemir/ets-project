package com.example.etsproject.service.concrete;

import com.example.etsproject.core.jwt.JwtService;
import com.example.etsproject.dto.CustomerDto;
import com.example.etsproject.dto.JwtResponse;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.entity.Authentication;
import com.example.etsproject.entity.Customer;
import com.example.etsproject.repository.CustomerRepository;
import com.example.etsproject.service.abstracts.AuthenticationService;
import com.example.etsproject.service.abstracts.CustomerService;
import com.example.etsproject.service.abstracts.AuthService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

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
    public DataResult<JwtResponse> login(LoginDto loginDto) throws Exception {
        Authentication a = new Authentication();
        var userToCheck = customerService.getByEmail( loginDto.getEmail());
        if(!userToCheck.isSuccess()){
            return new ErrorDataResult<>(null,"Email adresi yanlış.");
        }
        if(!passwordEncoder.matches(loginDto.getPassword(), userToCheck.getData().getPassword())){
            return new ErrorDataResult<>(null,"Parola yanlış.");
        }
        var result = jwtService.createJwtToken(loginDto);
        a.setCustomerId(userToCheck.getData().getId());
        a.setDateOfImport(new Date());
        a.setToken(result.getToken());
        authenticationService.add(a);

        return new SuccessDataResult<>(result);
    }

    @Override
    public Result changePassword() {
        return null;
    }
}
