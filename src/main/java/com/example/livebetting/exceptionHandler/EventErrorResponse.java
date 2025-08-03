package com.example.livebetting.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class EventErrorResponse {
    private int status;
    private HttpStatus httpStatus;
    private long timeStamp;
    private String message;

    public EventErrorResponse() {

    }

}