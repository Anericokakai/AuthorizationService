package com.auth.authorizationserver.Tdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private  String  customerName;
    private  String  customerEmail;
    private String  cutomerId;

}
