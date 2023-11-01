package com.auth.authorizationserver.Controller;

import com.auth.authorizationserver.Service.UserServiceImpl;
import com.auth.authorizationserver.Tdo.AuthRequest;
import com.auth.authorizationserver.Tdo.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authorize")
@AllArgsConstructor
public class CustomerController {
    private   final UserServiceImpl customerServiceImpl;

    @PostMapping("/newCustomer")

    public ResponseEntity<?> createNewCustomer(@RequestBody  @Valid UserRequest userRequest)  throws  Exception{

        var customer= customerServiceImpl.saveNewCustomer(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping("/hey")
    private  String hey(){
        return  "hey";
    }

    @PostMapping("/auth")
    public  ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest){

   var res= customerServiceImpl.authenticate(authRequest);
        return ResponseEntity.status(200).body(res);

    }
}
