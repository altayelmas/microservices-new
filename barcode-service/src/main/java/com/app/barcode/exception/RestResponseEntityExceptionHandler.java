package com.app.barcode.exception;

import com.app.barcode.model.dto.BarcodeResponseDto;
import com.app.barcode.model.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InputMismatchException.class })
    public ResponseEntity<BarcodeResponseDto> handleInputMismatchException(InputMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BarcodeResponseDto.builder()
                        .barcodeDtoList(new ArrayList<>())
                        .isSuccess(false)
                        .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = { BarcodeNotFoundException.class })
    public ResponseEntity<ProductResponseDto> handleNotFoundException(BarcodeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProductResponseDto.builder()
                        .productDtoList(new ArrayList<>())
                        .isSuccess(false)
                        .message(e.getMessage())
                .build());
    }

}
