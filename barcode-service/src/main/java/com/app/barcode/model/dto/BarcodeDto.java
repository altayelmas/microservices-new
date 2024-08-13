package com.app.barcode.model.dto;

import com.app.barcode.model.enums.BarcodeType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class BarcodeDto {

    private String barcode;
    private BarcodeType barcodeType;
    private String productCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarcodeDto that = (BarcodeDto) o;
        return Objects.equals(barcode, that.barcode) && barcodeType == that.barcodeType && Objects.equals(productCode, that.productCode);
    }
}
