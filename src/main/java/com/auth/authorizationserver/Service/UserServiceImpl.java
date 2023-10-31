package com.auth.authorizationserver.Service;

import com.auth.authorizationserver.Models.Roles;
import com.auth.authorizationserver.Models.Customers;
import com.auth.authorizationserver.Respository.CustomerRespository;
import com.auth.authorizationserver.Tdo.UserRequest;
import com.auth.authorizationserver.Tdo.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final ModelMapper modelMapper;
    private  final CustomerRespository customerRespository;
    private Roles roles;
    @Override
    public UserResponse saveNewCustomer(UserRequest userRequest) {

        Set<Roles> roles = new HashSet<>();
        Roles roles1=Roles.builder()
                .RoleName("ROLE_CUSTOMER").build();
        roles.add(roles1);




        Customers newCustomer= Customers.builder()
                .customerEmail(userRequest.getCustomerEmail())
                .customerName(userRequest.getCustomerName()
                        )
                .customerPassword(userRequest.getCustomerPassword())
                .roles(roles).build();



        Customers savedCustomer= customerRespository.save(newCustomer);

        return null;
    }
}
