package com.auth.authorizationserver.Service;

import com.auth.authorizationserver.Tdo.UserRequest;
import com.auth.authorizationserver.Tdo.UserResponse;

public interface UserService {

    public UserResponse saveNewCustomer(UserRequest userRequest);

}
