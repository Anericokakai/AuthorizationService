package com.auth.authorizationserver.Tdo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
   @NotNull(message = "name field cannot be blank")

   private String customerName;
   @Email(message = "email must be a valid email address")
   private  String customerEmail;
   @Length(min = 6,message = "password must have atleast 6 characters")
   private  String customerPassword;



}
