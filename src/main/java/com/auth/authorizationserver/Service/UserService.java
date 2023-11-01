package com.auth.authorizationserver.Service;

import com.auth.authorizationserver.Exceptions.UserExistException;
import com.auth.authorizationserver.Tdo.AuthRequest;
import com.auth.authorizationserver.Tdo.AuthResponse;
import com.auth.authorizationserver.Tdo.UserRequest;
import com.auth.authorizationserver.Tdo.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public UserResponse saveNewCustomer(UserRequest userRequest) throws UserExistException;

    public AuthResponse authenticate(AuthRequest authRequest);
}
