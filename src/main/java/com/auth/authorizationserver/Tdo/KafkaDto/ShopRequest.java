package com.auth.authorizationserver.Tdo.KafkaDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ShopRequest {


@NotBlank(message = "shop name cannot be blank")
    private String shopName;

@NotBlank(message = "ShopLocation cannot be blank")
    private  String shopLocation;
@NotBlank(message = "shop contact number cannot be blank")
    private String shopContact;

    private  String storeNumber;
}

