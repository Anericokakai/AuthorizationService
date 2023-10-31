package com.auth.authorizationserver.Controller;

import com.auth.authorizationserver.Service.UserServiceImpl;
import com.auth.authorizationserver.Tdo.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
@AllArgsConstructor
public class CustomerController {
    private   final UserServiceImpl customerServiceImpl;

    @PostMapping("/newCustomer")

    public ResponseEntity<?> createNewCustomer(@RequestBody UserRequest userRequest){

        var customer= customerServiceImpl.saveNewCustomer(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
