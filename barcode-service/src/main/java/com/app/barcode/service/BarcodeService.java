package com.app.barcode.service;

import com.app.barcode.model.dto.BarcodeCreateRequest;
import com.app.barcode.model.dto.BarcodeResponseDto;

public interface BarcodeService {

    BarcodeResponseDto createBarcode(BarcodeCreateRequest barcodeCreateRequest);

    String deleteBarcode(String productCode);

    BarcodeResponseDto getAll(String productCode);

}
