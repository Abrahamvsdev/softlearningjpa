package com.example.softlearning.applicationcore.entity.client.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "お客様")

public class JapaneseClientDTO {
    private String name, surname, email, address,dni,number,paymentMode,membershipLevel,registrationDate;
    private int antiquity;

    public JapaneseClientDTO() {
    }

    public JapaneseClientDTO(String name, String surname, String email, String address, String dni, String number,int antiquity,
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

    @JsonGetter("名前")
    public String getName() {
        return name;
    }

    @JsonSetter("名前")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("名字")
    public String getSurname() {
        return surname;
    }

    @JsonSetter("名字")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonGetter("メール")
    public String getEmail() {
        return email;
    }

    @JsonSetter("メール")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("住所")
    public String getAddress() {
        return address;
    }

    @JsonSetter("住所")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonGetter("DNI")
    public String getDni() {
        return dni;
    }

    @JsonSetter("DNI")
    public void setDni(String dni) {
        this.dni = dni;
    }

    @JsonGetter("番号")
    public String getNumber() {
        return number;
    }

    @JsonSetter("番号")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonGetter("支払い方法")
    public String getPaymentMode() {
        return paymentMode;
    }

    @JsonSetter("支払い方法")
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @JsonGetter("会員レベル")
    public String getMembershipLevel() {
        return membershipLevel;
    }

    @JsonSetter("会員レベル")
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @JsonGetter("登録日")
    public String getRegistrationDate() {
        return registrationDate;
    }

    @JsonSetter("登録日")
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @JsonGetter("古さ")
    public int getAntiquity() {
        return antiquity;
    }

    @JsonSetter("古さ")
    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

    // @Override
    // public String toString() {
    //     return "JapaneseClientDTO [address=" + address + ", antiquity=" + antiquity + ", dni=" + dni + ", email=" + email
    //             + ", membershipLevel=" + membershipLevel + ", name=" + name + ", number=" + number + ", paymentMode="
    //             + paymentMode + ", registrationDate=" + registrationDate + ", surname=" + surname + "]";
    // }



}
