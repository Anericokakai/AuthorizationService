package com.auth.authorizationserver.Tdo.KafkaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ValidUserDto {

    ShopRequest shopRequest;
    private  String  token;
}
