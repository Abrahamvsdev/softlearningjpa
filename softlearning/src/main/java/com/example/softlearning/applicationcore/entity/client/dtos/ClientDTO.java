package com.example.softlearning.applicationcore.entity.client.dtos;





public class ClientDTO {
    private final String name, surname, email, address,dni,number,paymentMode,membershipLevel,registrationDate;
    private final int antiquity;

    
    public ClientDTO(String name, String surname, String email, String address, String dni, String number,int antiquity,
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
