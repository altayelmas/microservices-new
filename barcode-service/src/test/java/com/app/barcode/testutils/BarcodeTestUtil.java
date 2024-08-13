package com.app.barcode.testutils;

import com.app.barcode.model.dto.*;
import com.app.barcode.model.entity.Barcode;
import com.app.barcode.model.enums.BarcodeType;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class BarcodeTestUtil {

    public static Barcode createBarcode(String barcode, BarcodeType barcodeType, String productCode) {
        return Barcode.builder()
                .barcode(barcode)
                .barcodeType(barcodeType)
                .productCode(productCode)
                .build();
    }

    public static BarcodeDto createBarcodeDto(String barcode, BarcodeType barcodeType, String productCode) {
        return BarcodeDto.builder()
                .barcode(barcode)
                .barcodeType(barcodeType)
                .productCode(productCode)
                .build();
    }

    public static BarcodeCreateRequest createBarcodeCreateRequest(String productCode, BarcodeType barcodeType) {
        return BarcodeCreateRequest.builder()
                .productCode(productCode)
                .barcodeType(barcodeType)
                .build();
    }

    public static ProductResponseDto createProductResponseDto(String productCode,
                                                              String productName,
                                                              String categoryName,
                                                              String brand,
                                                              String productUnit) {
        ProductDto productDto = createProductDto(productCode, productName, categoryName, brand, productUnit);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
        return ProductResponseDto.builder()
                .productDtoList(productDtoList)
                .message(HttpStatus.OK.toString())
                .isSuccess(true)
                .build();
    }

    private static ProductDto createProductDto(String productCode,
                                               String productName,
                                               String categoryName,
                                               String brand,
                                               String productUnit) {
        return ProductDto.builder()
                .productCode(productCode)
                .productName(productName)
                .categoryName(categoryName)
                .brand(brand)
                .productUnit(productUnit)
                .build();
    }

    public static CategoryDto createCategoryDto(String categoryName, String categoryCode) {
        return CategoryDto.builder()
                .name(categoryName)
                .categoryCode(categoryCode)
                .build();
    }

    public static BarcodeResponseDto createBarcodeResponseDto(String barcode, BarcodeType barcodeType, String productCode) {
        BarcodeDto barcodeDto = createBarcodeDto(barcode, barcodeType, productCode);
        List<BarcodeDto> barcodeDtoList = new ArrayList<>();
        barcodeDtoList.add(barcodeDto);

        return BarcodeResponseDto.builder()
                .barcodeDtoList(barcodeDtoList)
                .message("TestMessage")
                .isSuccess(true)
                .build();
    }

    public static List<BarcodeDto> createBarcodeDtoList(List<Barcode> barcodeList) {
        List<BarcodeDto> barcodeDtoList = new ArrayList<>();
        for (Barcode barcode : barcodeList) {
            barcodeDtoList.add(BarcodeDto.builder()
                            .barcode(barcode.getBarcode())
                            .barcodeType(barcode.getBarcodeType())
                            .productCode(barcode.getProductCode())
                    .build());
        }
        return barcodeDtoList;
    }
}
