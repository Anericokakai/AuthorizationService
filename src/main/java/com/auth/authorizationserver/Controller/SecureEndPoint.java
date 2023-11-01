package com.auth.authorizationserver.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SecureEndPoint {
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public  String  user(){
        return  "hello from the secure end point";

    }
}
