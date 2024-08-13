package com.app.barcode.repository;

import com.app.barcode.model.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BarcodeRepository extends JpaRepository<Barcode, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Barcode b WHERE b.productCode = :productCode")
    void deleteByProductCode(@Param("productCode") String productCode);
    //List<Barcode> deleteBarcodeByProductCode(String productCode);
    List<Barcode> findByProductCode(String productCode);

}
