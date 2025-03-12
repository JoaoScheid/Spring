package com.springRomario.aulaSpring1.controller;

import com.springRomario.aulaSpring1.model.dto.ExceptionHandlerResponseDTO;
import com.springRomario.aulaSpring1.model.exception.MesmoTitularException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionHandlerResponseDTO capturaDeErro(Exception exception) {
       return new ExceptionHandlerResponseDTO(exception.getMessage(), LocalDateTime.now());

    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionHandlerResponseDTO capturaDeErro(NoSuchElementException exception) {
        return new ExceptionHandlerResponseDTO(exception.getMessage(), LocalDateTime.now());

    }

    @ExceptionHandler(MesmoTitularException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionHandlerResponseDTO capturaDeErro(MesmoTitularException exception) {
        return new ExceptionHandlerResponseDTO(exception.getMessage(), LocalDateTime.now());
    }
}
