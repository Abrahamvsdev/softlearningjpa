package com.example.softlearning.applicationcore.entity.order.dtos;

public class OrderDTO {
    private final int reference;
    private final String description;
    private final String initDate;
    private final String finishDate;
    private final String receiverAddress;
    @SuppressWarnings("FieldMayBeFinal")
    private       String phoneContact;
    private final String idClient;
    private final String receiverPerson;
    private final String paymentDate;
    private final String deliveryDate;
    private final double height;
    private final double width;
    private final double weight;
    private final boolean fragile;
    private final double length;
    private final String shopCart;


    public OrderDTO(
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

    // Getters
    public int getRef() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public String getInitDate() {
        return initDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getReceiverPerson() {
        return receiverPerson;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isFragile() {
        return fragile;
    }

    public double getLength() {
        return length;
    }

    public String getShopCart() {
        return shopCart;
    }

    // public double getTotalPrice() {
    //     return totalPrice;
    // }

}