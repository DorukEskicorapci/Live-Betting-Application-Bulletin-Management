package com.example.livebetting.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(WrongArgumentException exc) {

        EventErrorResponse error = createErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());

        return new ResponseEntity<>(error, error.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(MissingArgumentException exc) {

        EventErrorResponse error = createErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());

        return new ResponseEntity<>(error, error.getHttpStatus());
    }



    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(Exception exc) {

        EventErrorResponse error = createErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());


        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    private EventErrorResponse createErrorResponse(HttpStatus status, String message) {
        EventErrorResponse error = new EventErrorResponse();

        error.setHttpStatus(status);
        error.setStatus(status.value());
        error.setMessage(message);
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }
}
