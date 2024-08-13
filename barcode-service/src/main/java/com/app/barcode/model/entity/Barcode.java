package com.app.barcode.model.entity;

import com.app.barcode.model.enums.BarcodeType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Barcode {

    @Id
    @GenericGenerator(name = "barcode_id", strategy = "com.app.barcode.generator.BarcodeIdGenerator")
    @GeneratedValue(generator = "barcode_id")
    private String barcode;
    @Enumerated(EnumType.STRING)
    private BarcodeType barcodeType;
    // Fetch Type lazy ve eager nedir
    private String productCode;

}
