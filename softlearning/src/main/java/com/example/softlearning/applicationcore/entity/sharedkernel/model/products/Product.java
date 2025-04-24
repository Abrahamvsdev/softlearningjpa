package com.example.softlearning.applicationcore.entity.sharedkernel.model.products;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.marketable.Marketable;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public abstract class Product implements Marketable {  

    protected String ident;
    protected double price;
    

    // //estos son de marketable
    protected boolean delayPay;
    protected double discount;
    protected String type;
    protected String payMethod;

    protected Product(){//ESTO SERVIRA PARA MAS ADELANTE

    }
        //Las clases abstractas no pueden ser static, esta es la que se usa para chekear cuando se crea el libro
        public void product(String ident, double price, boolean delayPay, double discount, String type, String payMethod) throws BuildException {
        StringBuilder errors = new StringBuilder();
        int errorCode;
        
        errorCode = setIdent(ident);
        if (errorCode != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        errorCode = setPrice(price);
        if (errorCode != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        errorCode = setDelayPay(delayPay);
        if (errorCode != 0 ) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        errorCode = setDiscount(discount);
        if (errorCode != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        errorCode = setPayMethod(payMethod);
        if (errorCode != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        errorCode = setType(type);
        if (errorCode != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
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

    
    //son override porque son del Marketable
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
        int errordelayPay = Check.istrue(delayPay);
        if (errordelayPay == 0) {
            this.delayPay = delayPay;
        }
        return errordelayPay;
    }

    public int setDiscount(double discount) {
        int errorDiscount =Check.range(price);
        if(errorDiscount==0){
            this.discount = discount;

        }
        return errorDiscount;
    }

    public int setType(String type) {
        int errorType= Check.minMaxLength(type, 3, 20);
        if(errorType== 0){
            this.type = type;
        }
        return errorType;
    }

    public int setPayMethod(String payMethod) {
        int errorPayMethod = Check.minMaxLength(payMethod, 3, 20);
        if(errorPayMethod == 0){
            this.payMethod = payMethod;
        }
        return errorPayMethod;
    }

    public int setIdent(String ident) {
        int errorIdent = Check.minLength(ident, 3);
        if (errorIdent == 0) {
            this.ident = ident;
        }
        return errorIdent;
    }

    public int setPrice(double price) {
        int errorPrice = Check.range(price);
        if (errorPrice == 0) {
            this.price = price;
        }
        return errorPrice;
    }

    public abstract String getDetails();
}
