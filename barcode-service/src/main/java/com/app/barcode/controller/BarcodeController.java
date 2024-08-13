package com.app.barcode.controller;

import com.app.barcode.model.dto.BarcodeCreateRequest;
import com.app.barcode.model.dto.BarcodeResponseDto;
import com.app.barcode.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/barcode")
@RequiredArgsConstructor
public class BarcodeController {

    private final BarcodeService barcodeService;

    @PostMapping
    public ResponseEntity<BarcodeResponseDto> create(@RequestBody BarcodeCreateRequest barcodeCreateRequest) {
        return new ResponseEntity<>(barcodeService.createBarcode(barcodeCreateRequest), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String productCode) {
        return new ResponseEntity<>(barcodeService.deleteBarcode(productCode), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BarcodeResponseDto> getAll(@RequestParam String productCode) {
        return new ResponseEntity<>(barcodeService.getAll(productCode), HttpStatus.OK);
    }

}
