package com.app.barcode.service;

import com.app.barcode.exception.BarcodeNotFoundException;
import com.app.barcode.model.dto.*;
import com.app.barcode.model.entity.Barcode;
import com.app.barcode.model.enums.BarcodeType;
import com.app.barcode.model.mapper.BarcodeMapper;
import com.app.barcode.repository.BarcodeRepository;
import com.app.barcode.service.impl.BarcodeServiceImpl;
import com.app.barcode.testutils.BarcodeTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BarcodeServiceImplTest {

    private BarcodeServiceImpl barcodeServiceImpl;
    private BarcodeRepository barcodeRepository;
    private BarcodeMapper barcodeMapper;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        barcodeRepository = mock(BarcodeRepository.class);
        barcodeMapper = mock(BarcodeMapper.class);
        restTemplate = mock(RestTemplate.class);


        barcodeServiceImpl = new BarcodeServiceImpl(barcodeRepository, barcodeMapper, restTemplate);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsMe_andProductUnitIsKilogram_andBarcodeTypeIsProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ME001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ME001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ME001",
                        "dummyName",
                        "MEYVE",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "MEYVE";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("MEYVE",
                        "ME"));

        Barcode barcode = BarcodeTestUtil.createBarcode("000000001", BarcodeType.PRODUCT, "ME001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "ME001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "ME001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsMe_andProductUnitIsPiece_andBarcodeTypeIsProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ME001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ME001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ME001",
                        "dummyName",
                        "MEYVE",
                        "dummyBrand",
                        "PIECE"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "MEYVE";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("MEYVE",
                        "ME"));

        Barcode barcode = BarcodeTestUtil.createBarcode("000000001", BarcodeType.PRODUCT, "ME001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "ME001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "ME001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsMe_andProductUnitIsKilogram_andBarcodeTypeIsRegister() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ME001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ME001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ME001",
                        "dummyName",
                        "MEYVE",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "MEYVE";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("MEYVE",
                        "ME"));

        Barcode barcode = BarcodeTestUtil.createBarcode("001", BarcodeType.REGISTER, "ME001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("001", BarcodeType.REGISTER, "ME001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("001", BarcodeType.REGISTER, "ME001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsBl_andProductUnitIsKilogram_andBarcodeTypeIsProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BL001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BL001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BL001",
                        "dummyName",
                        "BALIK",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BALIK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BALIK",
                        "BL"));

        Barcode barcode = BarcodeTestUtil.createBarcode("000000001", BarcodeType.PRODUCT, "BL001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "BL001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "BL001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsBl_andProductUnitIsKilogram_andBarcodeTypeIsScale() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BL001", BarcodeType.SCALE);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BL001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BL001",
                        "dummyName",
                        "BALIK",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BALIK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BALIK",
                        "BL"));

        Barcode barcode = BarcodeTestUtil.createBarcode("BL001001", BarcodeType.SCALE, "BL001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("BL001001", BarcodeType.SCALE, "BL001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("BL001001", BarcodeType.SCALE, "BL001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsBl_andProductUnitIsPiece_andBarcodeTypeIsRegister() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BL001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BL001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BL001",
                        "dummyName",
                        "BALIK",
                        "dummyBrand",
                        "PIECE"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BALIK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BALIK",
                        "BL"));

        Barcode barcode = BarcodeTestUtil.createBarcode("001", BarcodeType.REGISTER, "BL001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("001", BarcodeType.REGISTER, "BL001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("001", BarcodeType.REGISTER, "BL001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsEt_andProductUnitIsKilogram_andBarcodeTypeIsScale() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ET001", BarcodeType.SCALE);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ET001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ET001",
                        "dummyName",
                        "ET",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "ET";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("ET",
                        "ET"));

        Barcode barcode = BarcodeTestUtil.createBarcode("ET001001", BarcodeType.SCALE, "BL001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("ET001001", BarcodeType.SCALE, "ET001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("ET001001", BarcodeType.SCALE, "ET001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsBa_andProductUnitIsKilogram_andBarcodeTypeIsProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BA001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BA001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BA001",
                        "dummyName",
                        "BAKLAGIL",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BAKLAGIL";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BAKLAGIL",
                        "BA"));

        Barcode barcode = BarcodeTestUtil.createBarcode("000000001", BarcodeType.PRODUCT, "BA001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "BA001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "BA001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_whenProductCategoryIsIc_andProductUnitIsKilogram_andBarcodeTypeIsProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("IC001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "IC001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("IC001",
                        "dummyName",
                        "ICECEK",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "ICECEK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("ICECEK",
                        "IC"));

        Barcode barcode = BarcodeTestUtil.createBarcode("000000001", BarcodeType.PRODUCT, "IC001");

        when(barcodeRepository.save(any())).thenReturn(barcode);
        when(barcodeMapper.toDto(barcode)).thenReturn(BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "IC001"));

        BarcodeDto barcodeDto = BarcodeTestUtil.createBarcodeDto("000000001", BarcodeType.PRODUCT, "IC001");
        BarcodeDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest).getBarcodeDtoList().get(0);
        assertEquals(barcodeDto, result);
        verify(barcodeRepository).save(any());
        verify(barcodeMapper).toDto(barcode);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsMe_andProductUnitIsKilogram_andBarcodeTypeIsScale() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ME001", BarcodeType.SCALE);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ME001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ME001",
                        "dummyName",
                        "MEYVE",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "MEYVE";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("MEYVE",
                        "ME"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsBl_andProductUnitIsKilogram_andBarcodeTypeIsRegister() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BL001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BL001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BL001",
                        "dummyName",
                        "BALIK",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BALIK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BALIK",
                        "BL"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsBl_andProductUnitIsPiece_andBarcodeTypeIsNotRegister() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BL001", BarcodeType.PRODUCT);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BL001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BL001",
                        "dummyName",
                        "BALIK",
                        "dummyBrand",
                        "PIECE"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BALIK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BALIK",
                        "BL"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsEt_andBarcodeTypeIsNotScale() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("ET001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "ET001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("ET001",
                        "dummyName",
                        "ET",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "ET";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("ET",
                        "ET"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsBa_andBarcodeTypeIsNotProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("BA001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "BA001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("BA001",
                        "dummyName",
                        "BAKLAGIL",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "BAKLAGIL";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("BAKLAGIL",
                        "BA"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldThrowInputMismatchException_whenProductCategoryIsIc_andBarcodeTypeIsNotProduct() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("IC001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "IC001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenReturn(BarcodeTestUtil.createProductResponseDto("IC001",
                        "dummyName",
                        "ICECEK",
                        "dummyBrand",
                        "KILOGRAM"));

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + "ICECEK";
        when(restTemplate.getForObject(categoryUrl, CategoryDto.class))
                .thenReturn(BarcodeTestUtil.createCategoryDto("ICECEK",
                        "IC"));

        assertThrows(InputMismatchException.class, () -> barcodeServiceImpl.createBarcode(barcodeCreateRequest));
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void createBarcodeTest_shouldReturnEmptyBarcodeResponseDto_whenHttpClientErrorIsThrown() {
        BarcodeCreateRequest barcodeCreateRequest = BarcodeTestUtil.createBarcodeCreateRequest("IC001", BarcodeType.REGISTER);
        String productUrl = "http://localhost:8082/v1/api/product" + "?id=" + "IC001";
        when(restTemplate.getForObject(productUrl, ProductResponseDto.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Deneme"));

        BarcodeResponseDto result = barcodeServiceImpl.createBarcode(barcodeCreateRequest);
        assertTrue(result.getBarcodeDtoList().isEmpty());
        verifyNoInteractions(barcodeRepository);
        verifyNoInteractions(barcodeMapper);
    }

    @Test
    public void deleteBarcodeTest() {
        Barcode barcode = BarcodeTestUtil.createBarcode("ME001001", BarcodeType.SCALE, "ME001");
        List<Barcode> barcodeList = new ArrayList<>();
        barcodeList.add(barcode);

        when(barcodeRepository.findByProductCode("ME001")).thenReturn(barcodeList);
        String result = barcodeServiceImpl.deleteBarcode("ME001");
        assertEquals(result, HttpStatus.OK.toString());
    }

    @Test
    public void deleteBarcodeTest_shouldThrowBarcodeNotFoundException_whenBarcodeNotFound() {
        List<Barcode> barcodeList = new ArrayList<>();
        when(barcodeRepository.findByProductCode("ME001")).thenReturn(barcodeList);
        assertThrows(BarcodeNotFoundException.class, () -> barcodeServiceImpl.deleteBarcode("ME001"));
    }

    @Test
    public void getAllTest() {
        List<Barcode> barcodeList = new ArrayList<>();
        Barcode barcode = BarcodeTestUtil.createBarcode("ME001001", BarcodeType.SCALE, "ME001");
        barcodeList.add(barcode);
        when(barcodeRepository.findByProductCode("ME001")).thenReturn(barcodeList);

        List<BarcodeDto> barcodeDtoList = BarcodeTestUtil.createBarcodeDtoList(barcodeList);
        when(barcodeMapper.toDtoList(barcodeList)).thenReturn(barcodeDtoList);

        BarcodeResponseDto barcodeResponseDto = BarcodeTestUtil.createBarcodeResponseDto("ME001001", BarcodeType.SCALE, "ME001");

        BarcodeResponseDto result = barcodeServiceImpl.getAll("ME001");
        assertEquals(barcodeResponseDto.getBarcodeDtoList().get(0), result.getBarcodeDtoList().get(0));
    }

    @Test
    public void getAllTest_shouldThrowBarcodeNotFoundException_whenBarcodeNotFound() {
        when(barcodeRepository.findByProductCode("ME001")).thenReturn(new ArrayList<>());
        assertThrows(BarcodeNotFoundException.class, () -> barcodeServiceImpl.getAll("ME001"));
    }
}
