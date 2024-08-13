package com.app.productservice.exception;

import com.app.productservice.model.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ProductAlreadyExistsException.class })
    public ResponseEntity<ProductResponseDto> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        return new ResponseEntity<>(ProductResponseDto.builder()
                .productDtoList(new ArrayList<>())
                .message(e.getMessage())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { ProductNotFoundException.class })
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = { InputMismatchException.class })
    public ResponseEntity<String> handleInputMismatchException(InputMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
