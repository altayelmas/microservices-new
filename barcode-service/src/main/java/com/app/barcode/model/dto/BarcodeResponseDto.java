package com.app.barcode.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BarcodeResponseDto {
    private List<BarcodeDto> barcodeDtoList;
    private String message;
    private boolean isSuccess;
}
