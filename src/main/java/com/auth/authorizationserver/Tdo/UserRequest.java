package com.auth.authorizationserver.Tdo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
   @NotBlank(message = "name field cannot be blank")

   private String name;
   @NotBlank(message = "image filed cannot be blank")
   @Email(message = "email must be a valid email address")
   private  String email;
   @Length(min = 6,message = "password must have atleast 6 characters")
   private  String password;



}
