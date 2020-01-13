package com.gorin.restservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageDto extends AbstractDto {

    private String message;

    public MessageDto() {
    }

    public MessageDto(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
