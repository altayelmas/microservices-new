package com.app.barcode.generator;

import com.app.barcode.model.entity.Barcode;
import com.app.barcode.model.enums.BarcodeType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarcodeIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Barcode barcode = (Barcode) o;
        String prefix = barcode.getProductCode().substring(0, 5);

        int barcodeId = 1;
        String query;

        if (barcode.getBarcodeType() == BarcodeType.PRODUCT) {
            query = "SELECT MAX(CAST(barcode AS INT)) FROM barcode WHERE barcode_type = 'PRODUCT'";
        } else if (barcode.getBarcodeType() == BarcodeType.REGISTER)  {
            query = "SELECT MAX(CAST(barcode AS INT)) FROM barcode WHERE barcode_type = 'REGISTER'";
        } else {
            query = "SELECT MAX(CAST(SUBSTRING(barcode FROM 6) AS INT)) FROM barcode WHERE barcode_type = 'SCALE'";
        }

        try {
            Connection connection = sharedSessionContractImplementor.getJdbcConnectionAccess().obtainConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int maxId = resultSet.getInt(1);
                if (maxId > 0) {
                    barcodeId = maxId + 1;
                }
            }
        } catch (SQLException e) {
            throw new HibernateException("Database error during ID generation", e);
        }
        if (barcode.getBarcodeType() == BarcodeType.PRODUCT) {
            return String.format("%09d", barcodeId);
        } else if (barcode.getBarcodeType() == BarcodeType.REGISTER) {
            return String.format("%03d", barcodeId);
        } else {
            return String.format("%s%03d", prefix, barcodeId);
        }
    }
}
