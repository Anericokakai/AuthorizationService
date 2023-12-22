package com.auth.authorizationserver.Service.KafkaServices;

import com.auth.authorizationserver.Service.JWtService;
import com.auth.authorizationserver.Tdo.KafkaDto.ShopRequest;
import com.auth.authorizationserver.Tdo.KafkaDto.ValidUserDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {
private final JWtService jWtService;
private  final  KafkaProducer kafkaProducer;
private  final  static Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "shopwritesTopic",groupId = "shopGroup")

    public  void validateUserConsumer(ValidUserDto userDto){
try {
    LOGGER.info(String.format("the shop has been saved by ====> %s",userDto.toString()));
//    String  token=userDto.getToken();
//    jWtService.ValidateTopicToken(token);
//    kafkaProducer.isValidUserproducer(userDto);
}catch (Exception ex){
    System.out.println("AN ERROR OCCURED");
}



    }
}
