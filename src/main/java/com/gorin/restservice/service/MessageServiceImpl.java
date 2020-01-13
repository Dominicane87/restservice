package com.gorin.restservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorin.restservice.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class MessageServiceImpl implements MessageService{

    private final KafkaTemplate<Long, MessageDto> kafkaMessageTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageServiceImpl(KafkaTemplate<Long, MessageDto> kafkaMessageTemplate, ObjectMapper objectMapper) {
        this.kafkaMessageTemplate = kafkaMessageTemplate;
        this.objectMapper = objectMapper;
    }

    public MessageDto save(MessageDto dto) {
        
        return null;
    }

    public void send(MessageDto dto) {
        kafkaMessageTemplate.send("server.message", dto);
    }

    @KafkaListener(id = "Message", topics = {"server.message"}, containerFactory = "singleFactory")
    public void consume(MessageDto dto) {
        System.out.println(writeValueAsString(dto));
        save(dto);
    }

    private String writeValueAsString(MessageDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
