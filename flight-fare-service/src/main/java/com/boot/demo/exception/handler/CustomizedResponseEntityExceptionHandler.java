package com.boot.demo.exception.handler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

/**
 * The type Customized response entity exception handler.
 *
 * @author JayendraB
 * @createdOn 7 /10/2021
 */
@RestController
@ControllerAdvice
@Slf4j
public class CustomizedResponseEntityExceptionHandler {

    /**
     * Handle all exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        log.error("Occurred at : {}", ex.getStackTrace());
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle feign exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(FeignException.class)
    public final ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request) {
        log.error("Occurred at : {}", ex.getStackTrace());
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
