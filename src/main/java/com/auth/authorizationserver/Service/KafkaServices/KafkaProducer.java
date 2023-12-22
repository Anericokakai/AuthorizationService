package com.auth.authorizationserver.Service.KafkaServices;

import com.auth.authorizationserver.Tdo.KafkaDto.ValidUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class KafkaProducer {


    private  final KafkaTemplate<String , ValidUserDto> kafkaTemplate;


    public  void isValidUserproducer(ValidUserDto validUserDto){
        Message<ValidUserDto> message= MessageBuilder.withPayload(validUserDto)
                .setHeader(KafkaHeaders.TOPIC,"validOwnerTopic")
                .build();
        kafkaTemplate.send(message);

    }

}
