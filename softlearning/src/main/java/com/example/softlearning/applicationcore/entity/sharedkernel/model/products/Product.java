package com.example.softlearning.applicationcore.entity.sharedkernel.model.products;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.marketable.Marketable;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public abstract class Product implements Marketable {

    protected String ident;
    protected double price;

    protected boolean delayPay;
    protected double discount;
    protected String type;
    protected String payMethod;

    protected Product() {

    }

    public void product(String ident, double price, boolean delayPay, double discount, String type, String payMethod) throws BuildException {
        StringBuilder errors = new StringBuilder();
        int errorCode;

        if ((errorCode = setIdent(ident)) != 0) {
            errors.append("Bad ident: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = setPrice(price)) != 0) {
            errors.append("Bad price: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = setDelayPay(delayPay)) != 0) {
            errors.append("Bad delayPay: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = setDiscount(discount)) != 0) {
            errors.append("Bad discount: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = setPayMethod(payMethod)) != 0) {
            errors.append("Bad payMethod: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = setType(type)) != 0) {
            errors.append("Bad type: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if (errors.length() > 0) {
            throw new BuildException("Not possible to create the product: \n" + errors.toString());
        }
    }

    public String getIdent() {
        return this.ident;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean getDelayPay() {
        return delayPay;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPayMethod() {
        return payMethod;
    }

    //setters
    public int setDelayPay(boolean delayPay) {
        int errordelayPay = Check.checkBoolean(delayPay);
        if (errordelayPay == 0) {
            this.delayPay = delayPay;
        }
        return errordelayPay;
    }

    public int setDiscount(double discount) {
        int errorDiscount = Check.rangeDiscount(discount);
        if (errorDiscount == 0) {
            this.discount = discount;

        }
        return errorDiscount;
    }

    public int setType(String type) {
        int errorType = Check.checkLength(type, 3, 20);
        if (errorType == 0) {
            this.type = type;
        }
        return errorType;
    }

    public int setPayMethod(String payMethod) {
        int errorPayMethod = Check.checkLength(payMethod, 3, 20);
        if (errorPayMethod == 0) {
            this.payMethod = payMethod;
        }
        return errorPayMethod;
    }

    public int setIdent(String ident) {
        int errorIdent = Check.checkLength(ident, 3, 20);
        if (errorIdent == 0) {
            this.ident = ident;
        }
        return errorIdent;
    }

    public int setPrice(double price) {
        int errorPrice = Check.range(price, 0, 100000);
        if (errorPrice == 0) {
            this.price = price;
        }
        return errorPrice;
    }

    public abstract String getDetails();
}
