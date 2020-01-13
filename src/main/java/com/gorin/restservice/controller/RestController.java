package com.gorin.restservice.controller;


import com.gorin.restservice.dto.MessageDto;
import com.gorin.restservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/message")
class RestController{
    private final MessageService kafkaTemplate;


    @Autowired
    public RestController(MessageService kafkaTemplate) {
        if (kafkaTemplate==null){
            throw new IllegalArgumentException();
        }
        this.kafkaTemplate = kafkaTemplate;
    }

    @CrossOrigin
    @GetMapping(value = "/{message}")
    public void getAndSendMessage(@PathVariable("message") String message) {
        System.out.println(message);
        kafkaTemplate.send(new MessageDto(message));
    }


}
