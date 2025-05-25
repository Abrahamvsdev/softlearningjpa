package com.core.entities.order;

import com.example.softlearning.applicationcore.entity.order.model.OrderDetails;

public class OrderDetailsHelper extends OrderDetails {
    public int testSetAmount(int value) {
        return setAmount(value);
    }

    public int testSetPrice(double value) {
        return setPrice(value);
    }

    public int testSetDetailRef(String detailRef) {
        return setDetailRef(detailRef);
    }

    public int testSetDiscount(int setDiscount) {
        return setDiscount(setDiscount);
    }

}
