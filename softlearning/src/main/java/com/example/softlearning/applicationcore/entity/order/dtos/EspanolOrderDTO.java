package com.example.softlearning.applicationcore.entity.order.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Pedido")

public class EspanolOrderDTO {
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
    public EspanolOrderDTO(){};


    public EspanolOrderDTO(
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

    @JsonGetter("referencia")
    public int getReference() {
        return reference;
    }
    @JsonGetter("descripcion")
    public String getDescription() {
        return description;
    }
    @JsonGetter("fechaInicio")
    public String getInitDate() {
        return initDate;
    }
    @JsonGetter("fechaFinal")
    public String getFinishDate() {
        return finishDate;
    }
    @JsonGetter("direccionReceptor")
    public String getReceiverAddress() {
        return receiverAddress;
    }
    @JsonGetter("contactoTelefono")
    public String getPhoneContact() {
        return phoneContact;
    }
    @JsonGetter("idCliente")
    public String getIdClient() {
        return idClient;
    }
    @JsonGetter("personaReceptor")
    public String getReceiverPerson() {
        return receiverPerson;
    }
    @JsonGetter("fechaPago")
    public String getPaymentDate() {
        return paymentDate;
    }
    @JsonGetter("fechaEntrega")
    public String getDeliveryDate() {
        return deliveryDate;
    }
    @JsonGetter("altura")
    public double getHeight() {
        return height;
    }
    @JsonGetter("ancho")
    public double getWidth() {
        return width;
    }
    @JsonGetter("peso")
    public double getWeight() {
        return weight;
    }
    @JsonGetter("fragil")
    public boolean getFragile() {
        return fragile;
    }
    @JsonGetter("largo")
    public double getLength() {
        return length;
    }
    @JsonGetter("carritoCompra")
    public String getShopCart() {
        return shopCart;
    }
    //setters
    
    @JsonSetter("referencia")
    public void setReference(int reference) {
        this.reference = reference;
    }
    @JsonSetter("descripcion")
    public void setDescription(String description) {
        this.description = description;
    }
    @JsonSetter("fechaInicio")
    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }
    @JsonSetter("fechaFinal")
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    @JsonSetter("direccionReceptor")
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    @JsonSetter("contactoTelefono")
    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }
    @JsonSetter("idCliente")
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    @JsonSetter("personaReceptor")
    public void setReceiverPerson(String receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    @JsonSetter("fechaPago")
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    @JsonSetter("fechaEntrega")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    @JsonSetter("altura")
    public void setHeight(double height) {
        this.height = height;
    }
    @JsonSetter("ancho")
    public void setWidth(double width) {
        this.width = width;
    }
    @JsonSetter("peso")
    public void setWeight(double weight) {
        this.weight = weight;
    }
    @JsonSetter("fragil")
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }
    @JsonSetter("largo")
    public void setLength(double length) {
        this.length = length;
    }
    @JsonSetter("carritoCompra")
    public void setShopCart(String shopCart) {
        this.shopCart = shopCart;
    }


}
