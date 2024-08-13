package com.app.barcode.model.mapper;

import com.app.barcode.model.dto.BarcodeDto;
import com.app.barcode.model.entity.Barcode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BarcodeMapper {

    public BarcodeDto toDto(Barcode barcode) {
        return BarcodeDto.builder()
                .barcode(barcode.getBarcode())
                .barcodeType(barcode.getBarcodeType())
                .productCode(barcode.getProductCode())
                .build();
    }

    public List<BarcodeDto> toDtoList(List<Barcode> barcodes) {
        List<BarcodeDto> barcodeDtoList = new ArrayList<>();
        for (Barcode barcode : barcodes) {
            barcodeDtoList.add(toDto(barcode));
        }
        return barcodeDtoList;
    }
}
