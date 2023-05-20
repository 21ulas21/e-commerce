package com.bitirme.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private HttpStatus httpStatus;
}
