package com.example.softlearning.applicationcore.entity.client.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



@JacksonXmlRootElement(localName = "Clientes")

public class EspanolClientDTO {
    private String name, surname, email, address,dni,number,paymentMode,membershipLevel,registrationDate;
    private int antiquity;

    public EspanolClientDTO() {
    }

    public EspanolClientDTO(String name, String surname, String email, String address, String dni, String number,int antiquity,
            String paymentMode, String membershipLevel, String registrationDate ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.dni = dni;
        this.number = number;
        this.paymentMode = paymentMode;
        this.membershipLevel = membershipLevel;
        this.registrationDate = registrationDate;
        this.antiquity = antiquity;
    }

    @JsonGetter("nombre")
    public String getName() {
        return name;
    }

    @JsonSetter("nombre")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("apellido")
    public String getSurname() {
        return surname;
    }

    @JsonSetter("apellido")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonGetter("correo")
    public String getEmail() {
        return email;
    }

    @JsonSetter("correo")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("direccion")
    public String getAddress() {
        return address;
    }

    @JsonSetter("direccion")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonGetter("dni")
    public String getDni() {
        return dni;
    }

    @JsonSetter("dni")
    public void setDni(String dni) {
        this.dni = dni;
    }

    @JsonGetter("numero")
    public String getNumber() {
        return number;
    }

    @JsonSetter("numero")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonGetter("modoPago")
    public String getPaymentMode() {
        return paymentMode;
    }

    @JsonSetter("modoPago")
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @JsonGetter("nivelMembresia")
    public String getMembershipLevel() {
        return membershipLevel;
    }

    @JsonSetter("nivelMembresia")
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @JsonGetter("fechaRegistro")
    public String getRegistrationDate() {
        return registrationDate;
    }

    @JsonSetter("fechaRegistro")
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @JsonGetter("antiguedad")
    public int getAntiquity() {
        return antiquity;
    }

    @JsonSetter("antiguedad")
    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

}
