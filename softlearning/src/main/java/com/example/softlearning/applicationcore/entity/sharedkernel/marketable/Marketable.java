package com.example.softlearning.applicationcore.entity.sharedkernel.marketable;

public interface Marketable {
    boolean getDelayPay();
    double getDiscount();
    String getType();
    String getPayMethod();
}