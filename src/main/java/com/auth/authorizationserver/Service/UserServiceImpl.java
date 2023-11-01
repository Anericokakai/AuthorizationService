package com.auth.authorizationserver.Service;


import com.auth.authorizationserver.Exceptions.UserExistException;
import com.auth.authorizationserver.Models.Customers;
import com.auth.authorizationserver.Models.Roles;
import com.auth.authorizationserver.Respository.CustomerRespository;
import com.auth.authorizationserver.Respository.RolesRepository;
import com.auth.authorizationserver.Tdo.AuthRequest;
import com.auth.authorizationserver.Tdo.AuthResponse;
import com.auth.authorizationserver.Tdo.UserRequest;
import com.auth.authorizationserver.Tdo.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final ModelMapper modelMapper;
    private  final CustomerRespository customerRespository;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private  final  JWtService jWtService;
    private  final RolesRepository rolesRepository;


//    ! CREATE NEW USER

    @Override
    public UserResponse saveNewCustomer(UserRequest userRequest) throws UserExistException {


        if(!customerRespository.findCustomerBYEmail(userRequest.getEmail()).isEmpty()){
            throw new UserExistException("user already exist");
        }

        Roles roles=rolesRepository.findByName("CUSTOMER").get();


        Customers newCustomer= Customers.builder()
                .customerEmail(userRequest.getEmail())
                .customerName(userRequest.getName()
                        )
                .roles(Collections.singletonList(roles))

                .customerPassword(passwordEncoder.encode(userRequest.getPassword()))
                .build();



        Customers savedCustomer= customerRespository.save(newCustomer);

        return null;
    }


//    ! authenticate the user



    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        var user= customerRespository.findCustomerBYEmail(authRequest.getEmail())
                .orElseThrow(()->  new EntityNotFoundException("invalid login credentials"));

        String token= jWtService.generateToken(user);

        return AuthResponse.builder()
                .token(token).build();
    }
}
