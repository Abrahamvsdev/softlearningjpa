package com.example.softlearning.applicationcore.entity.order.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "注文")

public class JapaneseOrderDTO {
    private  int reference;
    private  String description;
    private  String initDate;
    private  String finishDate;
    private  String receiverAddress;
    private  String phoneContact;
    private  String idClient;
    private  String receiverPerson;
    private  String paymentDate;
    private  String deliveryDate;
    private  double height;
    private  double width;
    private  double weight;
    private  boolean fragile;
    private  double length;
    private  String shopCart;
    public JapaneseOrderDTO(){};


    public JapaneseOrderDTO(
        int reference, 
        String description, 
        String initDate, 
        String receiverAddress,
        String phoneContact, 
        String idClient, 
        String receiverPerson, 
        String paymentDate,
        String deliveryDate, 
        String finishDate, 
        double height, 
        double width, 
        double weight, 
        boolean fragile,
        double length, 
        String shopCart 
        ) {
            
        this.reference = reference;
        this.description = description;
        this.initDate = initDate;
        this.receiverAddress = receiverAddress;
        this.phoneContact = phoneContact;
        this.idClient = idClient;
        this.receiverPerson = receiverPerson;
        this.paymentDate = paymentDate;
        this.deliveryDate = deliveryDate;
        this.finishDate = finishDate;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.fragile = fragile;
        this.length = length;
        this.shopCart = shopCart;
        
        
    }

    @JsonGetter("参照")
    public int getReference() {
        return reference;
    }
    @JsonGetter("説明")
    public String getDescription() {
        return description;
    }
    @JsonGetter("初期日")
    public String getInitDate() {
        return initDate;
    }
    @JsonGetter("終了日")
    public String getFinishDate() {
        return finishDate;
    }
    @JsonGetter("受信者住所")
    public String getReceiverAddress() {
        return receiverAddress;
    }
    @JsonGetter("電話連絡")

    public String getPhoneContact() {
        return phoneContact;
    }
    @JsonGetter("顧客ID")
    public String getIdClient() {
        return idClient;
    }
    @JsonGetter("受信者")
    public String getReceiverPerson() {
        return receiverPerson;
    }
    @JsonGetter("支払日")
    public String getPaymentDate() {
        return paymentDate;
    }
    @JsonGetter("配達日")
    public String getDeliveryDate() {
        return deliveryDate;
    }
    @JsonGetter("高さ")
    public double getHeight() {
        return height;
    }
    @JsonGetter("幅")
    public double getWidth() {
        return width;
    }
    @JsonGetter("重量")
    public double getWeight() {
        return weight;
    }
    @JsonGetter("壊れやすい")
    public boolean getFragile() {
        return fragile;
    }
    @JsonGetter("長さ")
    public double getLength() {
        return length;
    }
    @JsonGetter("ショッピングカート")
    public String getShopCart() {
        return shopCart;
    }
    //setters
    @JsonSetter("参照")
    public void setReference(int reference) {
        this.reference = reference;
    }
    @JsonSetter("説明")
    public void setDescription(String description) {
        this.description = description;
    }
    @JsonSetter("初期日")
    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }
    @JsonSetter("終了日")
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    @JsonSetter("受信者住所")
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    @JsonSetter("電話連絡")
    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }
    @JsonSetter("顧客ID")
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    @JsonSetter("受信者")
    public void setReceiverPerson(String receiverPerson) {
        this.receiverPerson = receiverPerson;
    }
    @JsonSetter("支払日")
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    @JsonSetter("配達日")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    @JsonSetter("高さ")
    public void setHeight(double height) {
        this.height = height;
    }   

    @JsonSetter("幅")
    public void setWidth(double width) {
        this.width = width;
    }
    @JsonSetter("重量")
    public void setWeight(double weight) {
        this.weight = weight;
    }
    @JsonSetter("壊れやすい")
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }
    @JsonSetter("長さ")
    public void setLength(double length) {
        this.length = length;
    }
    @JsonSetter("ショッピングカート")
    public void setShopCart(String shopCart) {
        this.shopCart = shopCart;
    }
    // @Override
    // public String toString() {
    //     return "OrderDTO [description=" + description + ", deliveryDate=" + deliveryDate + ", finishDate=" + finishDate
    //             + ", fragile=" + fragile + ", height=" + height + ", idClient=" + idClient + ", initDate=" + initDate
    //             + ", length=" + length + ", paymentDate=" + paymentDate + ", phoneContact=" + phoneContact
    //             + ", receiverAddress=" + receiverAddress + ", receiverPerson=" + receiverPerson + ", reference="
    //             + reference + ", shopCart=" + shopCart + ", weight=" + weight + ", width=" + width + "]";
    // }







}