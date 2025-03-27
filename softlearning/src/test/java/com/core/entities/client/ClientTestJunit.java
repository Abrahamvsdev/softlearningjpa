package com.core.entities.client;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.client.model.Client;

public class ClientTestJunit {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    // Valores válidos para todos los campos
    private final String validName = "Ana";
    private final String validSurname = "Gómez";
    private final String validEmail = "ana.gomez@example.com";
    private final String validAddress = "Calle Mayor 5";
    private final String validDni = "87654321X";
    private final String validNumber = "600123456";
    private final int validAntiquity = 3;
    private final String validPaymentMode = "PayPal";
    private final String validMembershipLevel = "Premium";
    private final String validRegistrationDate = "02-03-2025";

    private Client validClient;

    @BeforeEach
    public void setUp() throws Exception {
        validClient = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni,
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
        );
    }

    //--------------------- Tests para campos específicos de Client ---------------------
    @Test
    public void testInvalidPaymentMode_Null() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, null, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por paymentMode nulo");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nNo puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidPaymentMode_TooShort() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, "A", validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por paymentMode demasiado corto");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nHas introducido pocos caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidPaymentMode_TooLong() {
        String longPaymentMode = "AAAaaaAAaaaAAAaaaaaaaaaaaaaaaaaa";
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, longPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por paymentMode demasiado largo");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nHas introducido demasiados caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidMembershipLevel_InvalidFormat() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, "", validRegistrationDate
            );
            fail("Debería fallar por membershipLevel inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nNo puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testSetRegistrationDate_Valid() throws Exception {
        Client client = Client.getInstance(validName, validSurname, validEmail, validAddress, validDni,
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "29-02-2024");
        assertEquals(0, client.setRegistrationDate("29-02-2024")); // Año bisiesto
    }

    @Test
    public void testInvalidRegistrationDate_InvalidFormat() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "2025/03/02"
            );
            fail("Debería fallar por formato de fecha incorrecto");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nFormato correcto pero no válida\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidRegistrationDate_InvalidMonth() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "15-13-2025"
            );
            fail("Debería fallar por mes inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nFormato correcto pero no válida\n", e.getMessage());
        }
    }

    //--------------------- Tests para campos heredados de Person ---------------------
    @Test
    public void testInvalidName_TooLong() {
        String longName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        try {
            Client.getInstance(
                    longName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por nombre demasiado largo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido demasiados caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidEmail_InvalidFormat() {
        try {
            Client.getInstance(
                    validName, validSurname, "correo-invalido", validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por email inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nFormato de email incorrecto\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidDni_InvalidFormat() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, "DNI_INVALIDO",
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por DNI inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nDNI no válido\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidAntiquity_Negative() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, -1, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por antigüedad negativa");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido un numero negativo\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidSurname_TooShort() {
        try {
            Client.getInstance(
                    validName, "A", validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por apellido demasiado corto");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido pocos caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidSurname_TooLong() {
        String longSurname = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        try {
            Client.getInstance(
                    validName, longSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar por apellido demasiado largo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido demasiados caracteres\n", e.getMessage());
        }
    }

    @Test
public void testValidDni() throws Exception {
    Client client = Client.getInstance(
        validName, validSurname, validEmail, validAddress, "99999999R",
        validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
    );
    assertEquals("99999999R", client.getDni());
}

    //--------------------- Tests de getters ---------------------
    @Test
    public void testGetPaymentMode() {
        assertEquals(validPaymentMode, validClient.getPaymentMode());
    }

    @Test
    public void testGetMembershipLevel() {
        assertEquals(validMembershipLevel, validClient.getMembershipLevel());
    }

    @Test
    public void testGetRegistrationDate() {
        assertEquals("02-03-2025", validClient.getRegistrationDate());
    }

    //--------------------- Tests de métodos heredados ---------------------
    @Test
    public void testGetContactData() {
        String expected = "Client: Ana Gómez, Email: ana.gomez@example.com, Payment Mode: PayPal";
        assertEquals(expected, validClient.getContactData());
    }

    @Test
    public void testGetDetails() {
        String expected = "Client Details: \n"
                + "Name: Ana\n"
                + "Surname: Gómez\n"
                + "Email: ana.gomez@example.com\n"
                + "Address: Calle Mayor 5\n"
                + "DNI: 87654321X\n"
                + "Number: 600123456\n"
                + "Antiquity: 3\n"
                + "Payment Mode: PayPal\n"
                + "Membership Level: Premium\n"
                + "Registration Date: 02-03-2025";
        assertEquals(expected, validClient.getDetails());
    }

    //--------------------- Tests de setters ---------------------
    @Test
    public void testSetPaymentMode_Valid() throws Exception {
        Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni,
                validNumber, "CreditCard", validAntiquity, validMembershipLevel, validRegistrationDate
        );
        assertEquals(0, client.setPaymentMode("Transfer"));
    }

    @Test
    public void testSetRegistrationDate_Invalid() throws Exception {
        Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni,
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
        );
        assertEquals(-14, client.setRegistrationDate("30-02-2025"));
    }

    @Test
    public void testInvalidMembershipLevel_TooLong() {
        String longMembership = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, longMembership, validRegistrationDate
            );
            fail("Debería fallar por membershipLevel demasiado largo");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nHas introducido demasiados caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidRegistrationDate_InvalidDay() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "32-01-2020"
            );
            fail("Debería fallar por día inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nFormato correcto pero no válida\n", e.getMessage());
        }
    }

// Test para setMembershipLevel
    @Test
    public void testSetMembershipLevel_Invalid() throws Exception {
        Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni,
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
        );
        assertEquals(-7, client.setMembershipLevel("MembershipLevelTooLong"));
    }

// Test para herencia: número de teléfono inválido
    @Test
    public void testInvalidNumber() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    "12", validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Debería fallar");
        } catch (Exception e) {
            String expected = "No es posible crear al tipo: \nHas introducido pocos caracteres\n";
            assertEquals(expected, e.getMessage());
        }
    }
}
