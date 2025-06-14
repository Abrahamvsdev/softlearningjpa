package com.example.softlearning.applicationcore.entity.client.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientDTO {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Id
    @Column(name = "dni") //El dni será mi ID
    private String dni;
    @Column(name = "number")
    private String number;
    @Column(name = "paymentMode")
    private String paymentMode;
    @Column(name = "membershipLevel")
    private String membershipLevel;
    @Column(name = "registrationDate")
    private String registrationDate;
    @Column(name = "antiquity")
    private int antiquity;

    protected ClientDTO() {
    }

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
