package com.app.barcode.service.impl;

import com.app.barcode.exception.BarcodeNotFoundException;
import com.app.barcode.model.dto.*;
import com.app.barcode.model.mapper.BarcodeMapper;
import com.app.barcode.model.entity.Barcode;
import com.app.barcode.model.enums.BarcodeType;
import com.app.barcode.repository.BarcodeRepository;
import com.app.barcode.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;
    private final BarcodeMapper barcodeMapper;
    private final RestTemplate restTemplate;

    @Override
    public BarcodeResponseDto createBarcode(BarcodeCreateRequest barcodeCreateRequest) {
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + barcodeCreateRequest.getProductCode();

        try {
            ProductResponseDto productResponseDto = restTemplate.getForObject(productUrl, ProductResponseDto.class);

            assert productResponseDto != null;
            String categoryName = productResponseDto.getProductDtoList().get(0).getCategoryName();

            String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + categoryName;

            CategoryDto categoryDto = restTemplate.getForObject(categoryUrl, CategoryDto.class);

            assert categoryDto != null;
            String productCategory = categoryDto.getCategoryCode();
            String productUnit = productResponseDto.getProductDtoList().get(0).getProductUnit();

            if (productCategory.equals("ME") &&
                    productUnit.equals("KILOGRAM") &&
                    !(barcodeCreateRequest.getBarcodeType() == BarcodeType.PRODUCT || barcodeCreateRequest.getBarcodeType() == BarcodeType.REGISTER)) {
                throw new InputMismatchException("For FRUIT products in KILOGRAM, only BARCODE TYPE PRODUCT or REGISTER is allowed.");
            } else if (productCategory.equals("BL") &&
                    productUnit.equals("KILOGRAM") &&
                    !(barcodeCreateRequest.getBarcodeType() == BarcodeType.PRODUCT || barcodeCreateRequest.getBarcodeType() == BarcodeType.SCALE)) {
                throw new InputMismatchException("For FISH products in KILOGRAM, only BARCODE TYPE PRODUCT or SCALE is allowed.");
            } else if (productCategory.equals("BL") &&
                    productUnit.equals("PIECE") &&
                    !(barcodeCreateRequest.getBarcodeType() == BarcodeType.REGISTER)) {
                throw new InputMismatchException("For FISH products in PIECE, only BARCODE TYPE REGISTER is allowed.");
            } else if ((productCategory.equals("ET")) && !(barcodeCreateRequest.getBarcodeType() == BarcodeType.SCALE)) {
                throw new InputMismatchException("For MEAT products, only BARCODE TYPE SCALE is allowed.");
            } else if (productCategory.equals("BA") ||
                    productCategory.equals("IC")) {
                if (!(barcodeCreateRequest.getBarcodeType() == BarcodeType.PRODUCT)) {
                    throw new InputMismatchException("For LEGUMES and DRINKS products, only BARCODE TYPE PRODUCT is allowed.");
                }
            }

            Barcode barcode = Barcode.builder()
                    .barcodeType(barcodeCreateRequest.getBarcodeType())
                    .productCode(barcodeCreateRequest.getProductCode())
                    .build();

            BarcodeDto barcodeDto = barcodeMapper.toDto(barcodeRepository.save(barcode));

            List<BarcodeDto> barcodeDtoList = new ArrayList<>();
            barcodeDtoList.add(barcodeDto);

            return BarcodeResponseDto.builder()
                    .barcodeDtoList(barcodeDtoList)
                    .message(HttpStatus.CREATED.toString())
                    .isSuccess(true)
                    .build();

        } catch (HttpClientErrorException e) {
            return BarcodeResponseDto.builder()
                    .barcodeDtoList(new ArrayList<>())
                    .message(e.getResponseBodyAsString())
                    .isSuccess(false)
                    .build();
        }
    }

    @Override
    public String deleteBarcode(String productCode) {
        List<Barcode> barcodeList = barcodeRepository.findByProductCode(productCode);
        if (barcodeList.isEmpty()) {
            throw new BarcodeNotFoundException("Barcode with given Product Code not found");
        }
        barcodeRepository.deleteByProductCode(productCode);
        return HttpStatus.OK.toString();
    }

    @Override
    public BarcodeResponseDto getAll(String productCode) {
        List<Barcode> barcodeList = barcodeRepository.findByProductCode(productCode);
        if (barcodeList.isEmpty()) {
            throw new BarcodeNotFoundException("Barcode with given product code not found.");
        }
        return BarcodeResponseDto.builder()
                .barcodeDtoList(barcodeMapper.toDtoList(barcodeList))
                .message(HttpStatus.OK.toString())
                .isSuccess(true)
                .build();
    }

}
