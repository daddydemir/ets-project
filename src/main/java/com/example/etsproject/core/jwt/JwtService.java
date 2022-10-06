package com.example.etsproject.core.jwt;

import com.example.etsproject.dto.JwtResponse;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.entity.Customer;
import com.example.etsproject.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;

    // @Autowired is throw error
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(LoginDto loginDto) throws Exception{
        authenticate(loginDto.getEmail(),loginDto.getPassword());

        UserDetails userDetails = loadUserByUsername(loginDto.getEmail());
        String generatedToken = jwtUtil.generateToken(userDetails);

        Customer customer = customerService.findByEmail(loginDto.getEmail());
        return new JwtResponse(customer, generatedToken);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(username);

        if(customer != null){
            return new User(customer.getEmail(),customer.getPassword(), new ArrayList<>()); // handle authority
        }else{
            throw new UsernameNotFoundException("Bu mail adresine kayıtlı hesap bulunamadı."); // vulnerability ?
        }

    }

    private void authenticate(String email, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (Exception e){
            // handle error
        }
    }
}
