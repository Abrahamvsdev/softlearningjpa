package com.example.softlearning.applicationcore.entity.order.model;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class OrderDetails {

    protected int amount;
    protected String detailRef;
    protected double price;
    protected int discount;

    //string ref, int amount, double price, double discount, asi los tiene jose
    public static OrderDetails getInstance(int amount, String detailRef, double price, int discount) throws ServiceException {

        OrderDetails od = new OrderDetails();
        StringBuilder errors = new StringBuilder();
        int errorCode;

        if ((errorCode = od.setAmount(amount)) != 0) {
            errors.append("Bad amount: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = od.setDetailRef(detailRef)) != 0) {
            errors.append("Bad detailRef: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = od.setPrice(price)) != 0) {
            errors.append("Bad price: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = od.setDiscount(discount)) != 0) {
            errors.append("Bad discount: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (errors.length() > 0) {
            throw new ServiceException(errors.toString());
        }

        return od;
    }

    // getters 
    public double calculateSubtotal() {
        return price * (1 - discount / 100.0) * amount; 
    }

    public int getAmount() {
        return amount;
    }

    public String getDetailRef() {
        return detailRef;
    }

    public double getPrice() {

        return price;
    }

    public double getDiscount() {
        return discount;
    }


    // Setters
    protected  int setAmount(int amount) {
        int errorAmount = Check.range(amount, 1, 1000);
        if (errorAmount != 0) {
            return errorAmount;
        }
        this.amount = amount;
        return 0;
    }

    protected int setDetailRef(String detailRef) {
        int errorDetailRef = Check.checkLength(detailRef, 1, 10);
        if (errorDetailRef != 0) {
            return errorDetailRef;
        }
        this.detailRef = detailRef;
        return 0;
    }

    protected int setPrice(double price) {
        int errorPrice = Check.range(price, 1, 100000);
        if (errorPrice != 0) {
            return errorPrice;
        }
        this.price = price;
        return 0;
    }

    protected int setDiscount(int discount) {
        int errorDiscount = Check.rangeDiscount(discount);
        if (errorDiscount != 0) {
            return errorDiscount;
        }
        this.discount = discount;
        return 0;
    }

    //agregar metodo toString
    public String getDetailstoString() {
        return "amount:" + this.amount
                + ", detailRef:" + this.detailRef
                + ", price:" + this.price
                + ", discount:" + this.discount
                + ", subtotal:" + this.calculateSubtotal();
    }

}
