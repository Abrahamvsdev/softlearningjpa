package com.example.softlearning.applicationcore.entity.client.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientDTO {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    private final String name;
    @Column(name = "surname")
    private final String surname;
    @Column(name = "email")
    private final String email;
    @Column(name = "address")
    private final String address;
    @Column(name = "dni")
    private final String dni;
    @Column(name = "number")
    private final String number;
    @Column(name = "paymentMode")
    private final String paymentMode;
    @Column(name = "membershipLevel")
    private final String membershipLevel;
    @Column(name = "registrationDate")
    private final String registrationDate;
    @Column(name = "antiquity")
    private final int antiquity;

    public ClientDTO(String name, String surname, String email, String address, String dni, String number, int antiquity,
            String paymentMode, String membershipLevel, String registrationDate) {
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDni() {
        return dni;
    }

    public String getNumber() {
        return number;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public int getAntiquity() {
        return antiquity;
    }

}
