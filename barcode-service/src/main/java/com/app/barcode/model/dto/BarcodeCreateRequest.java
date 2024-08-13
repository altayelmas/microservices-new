package com.app.barcode.model.dto;

import com.app.barcode.model.enums.BarcodeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeCreateRequest {

    private String productCode;
    private BarcodeType barcodeType;

}
