package com.gorin.restservice.service;


import com.gorin.restservice.dto.MessageDto;

public interface MessageService {

    MessageDto save(MessageDto dto);

    void send(MessageDto dto);

    void consume(MessageDto dto);
}
