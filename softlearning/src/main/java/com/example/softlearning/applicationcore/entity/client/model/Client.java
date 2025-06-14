package com.example.softlearning.applicationcore.entity.client.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.stakeholders.Person;

public class Client extends Person {
    protected String paymentMode;      // Metodo de pago
    protected String membershipLevel;   // Nivel
    protected LocalDate registrationDate; // Cuando se registro
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    protected Client() {
        
    }

    public static Client getInstance(String name, String surname, String email, String address, String dni, String number, String paymentMode, int antiquity, String membershipLevel, String registrationDate) throws Exception {
        Client client = new Client();

        StringBuilder errors = new StringBuilder();
        int errorCode;

        
        client.person(name, surname, email, address, dni, number, antiquity);

        if ((errorCode = client.setPaymentMode(paymentMode)) != 0) {
            errors.append("Bad paymentMode: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = client.setMembershipLevel(membershipLevel)) != 0) {
            errors.append("Bad membershipLevel: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = client.setRegistrationDate(registrationDate)) != 0) {
            errors.append("Bad registrationDate: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (errors.length() > 0) {
            throw new Exception("No es posible crear el cliente: \n" + errors.toString());
        }
        
        return client;
    }

    // Getters
    public String getPaymentMode() {
        return paymentMode;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getRegistrationDate() {
        return this.registrationDate.format(formatter);
    }

    // setters
    public int setPaymentMode(String paymentMode) {
        int errorCode = Check.checkLength(paymentMode, 3, 20);
        if (errorCode == 0) {
            this.paymentMode = paymentMode;
        }
        return errorCode;
    }

    public int setMembershipLevel(String membershipLevel) {
        int errorCode = Check.checkLength(membershipLevel, 3, 20);
        if (errorCode == 0) {
            this.membershipLevel = membershipLevel;
        }
        return errorCode;
    }

    public int setRegistrationDate(String registrationDate) {
        int errorCode = Check.isValidDate(registrationDate);
        if (errorCode == 0) {
            this.registrationDate = LocalDate.parse(registrationDate, formatter);
        }
        return errorCode;
    }

    
    @Override
    public String getContactData() {
        return "Client: " + getName() + " " + getSurname() + ", Email: " + getEmail() + ", Payment Mode: " + paymentMode;
    }

    
    @Override
    public String getDetails() {
        return "Client Details: \n" +
                "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Address: " + getAddress() + "\n" +
                "DNI: " + getDni() + "\n" +
                "Number: " + getNumber() + "\n" +
                "Antiquity: " + getAntiquity() + "\n" +
                "Payment Mode: " + getPaymentMode() + "\n" +
                "Membership Level: " + getMembershipLevel() + "\n" +
                "Registration Date: " + getRegistrationDate();
    }
}

