package com.core.entities.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.client.model.Client;

public class ClientTestJunit {

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
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate);
    }

    /* getInstance */

    @Test
    public void testClientGetInstanceOk() {
        try {
            Client c = Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate);
            
            assertNotNull(c);
        } catch (Exception e) {
            fail("No deberia fallar, todos los campos son correctos");
        }
    }

    @Test
    public void testClientGetInstanceInvalidPaymentMode() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, null, validAntiquity, validMembershipLevel, validRegistrationDate);
            fail("Debería fallar por paymentMode nulo");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nBad paymentMode: No puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testClientGetInstanceInvalidMembershipLevel() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, "", validRegistrationDate);
            fail("Debería fallar por membershipLevel inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nBad membershipLevel: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testClientGetInstanceInvalidRegistrationDate() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "2025/03/02");
            fail("Debería fallar registrationDate, por formato de fecha incorrecto");
        } catch (Exception e) {
            assertEquals("No es posible crear el cliente: \nBad registrationDate: Formato de fecha incorrecto\n", e.getMessage());
        }
    }

    @Test
    public void testClientGetInstanceInvalidAll() {
        try {
            Client.getInstance(
                    validName, validSurname, validEmail, validAddress, validDni,
                    validNumber, "", validAntiquity, "", "");
            fail("Debería fallar por fallo en varios campos");
        } catch (Exception e) {
            String expected = "No es posible crear el cliente: \n" +
                "Bad paymentMode: No puede estar vacío\n" +
                "Bad membershipLevel: No puede estar vacío\n" +
                "Bad registrationDate: No puede estar vacío\n";
            assertEquals(expected, e.getMessage());
        }
    }

   

    // --------------------- Tests de getters ---------------------
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
        assertEquals(validRegistrationDate, validClient.getRegistrationDate());
    }

    @Test
    public void testGetName() {
        assertEquals(validName, validClient.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals(validSurname, validClient.getSurname());
    }

    @Test
    public void testGetEmail() {
        assertEquals(validEmail, validClient.getEmail());
    }

    @Test
    public void testGetAddress() {
        assertEquals(validAddress, validClient.getAddress());
    }

    @Test
    public void testGetDNI() {
        assertEquals(validDni, validClient.getDni());
    }

    @Test
    public void testGetNumber() {
        assertEquals(validNumber, validClient.getNumber());
    }

    @Test
    public void testGetAntiquity() {
        assertEquals(validAntiquity, validClient.getAntiquity());
    }

    // --------------------- Tests de setters ---------------------
   

    @Test
    public void testSetPaymentMode() {
        assertEquals(0, validClient.setPaymentMode(validPaymentMode));
    }

    @Test
    public void testSetPaymentModeShort() {
        assertEquals(-3, validClient.setPaymentMode("a"));
    }

    @Test
    public void testSetPaymentModeLong() {
        assertEquals(-7, validClient.setPaymentMode("1234567890123456123123123123123123123123"));
    }

    @Test
    public void testSetPaymentModeNull() {
        assertEquals(-1, validClient.setPaymentMode(null));
    }

    @Test
    public void testSetPaymentModeEmpty() {
        assertEquals(-2, validClient.setPaymentMode(""));
    }

    @Test
    public void testSetPaymentModeSpaceString() {
        assertEquals(-2, validClient.setPaymentMode("   "));
    }

    @Test
    public void testSetAntiquity() {
        assertEquals(0, validClient.setAntiquity(validAntiquity));
    }

    @Test
    public void testSetMembershipLevel() {
        assertEquals(0, validClient.setMembershipLevel(validMembershipLevel));
    }

    @Test
    public void testSetMembershipLevelShort() {
        assertEquals(-3, validClient.setMembershipLevel("a"));
    }

    @Test
    public void testSetMembershipLevelLong() {
        assertEquals(-7, validClient.setMembershipLevel("membershipleveldemasiadolargosounmontondelargo"));
    }

    @Test
    public void testSetMembershipLevelNull() {
        assertEquals(-1, validClient.setMembershipLevel(null));
    }

    @Test
    public void testSetMembershipLevelEmpty() {
        assertEquals(-2, validClient.setMembershipLevel(""));
    }

    @Test
    public void testSetMembershipLevelString() {
        assertEquals(-2, validClient.setMembershipLevel("   "));
    }

    @Test
    public void testSetRegistrationDate() {
        assertEquals(0, validClient.setRegistrationDate(validRegistrationDate));
    }

    @Test
    public void testSetRegistrationDateInvalidDate() {
        assertEquals(-14, validClient.setRegistrationDate("30-02-2023"));
    }

    @Test
    public void testSetRegistrationDateIncorrectFormat() {
        assertEquals(-14, validClient.setRegistrationDate("2023-08-15"));
    }

    @Test
    public void testSetRegistrationDateWithLetters() {
        assertEquals(-14, validClient.setRegistrationDate("aa-bb-cccc"));
    }

    @Test
    public void testSetRegistrationDateWithAnotherCharacters() {
        assertEquals(-14, validClient.setRegistrationDate("15/08/2023"));
    }

    @Test
    public void testSetRegistrationDateNull() {
        assertEquals(-1, validClient.setRegistrationDate(null));
    }

    @Test
    public void testSetRegistrationDateEmpty() {
        assertEquals(-2, validClient.setRegistrationDate(""));
    }

    @Test
    public void testSetRegistrationDateWithSpace() {
        assertEquals(-2, validClient.setRegistrationDate("    "));
    }

    // --------------------- Tests de métodos heredados ---------------------
    @Test
    public void testGetContactData() {
        String expected = "Client: " + validName + " " + validSurname + ", Email: " + validEmail + ", Payment Mode: "
                + validPaymentMode;
        assertEquals(expected, validClient.getContactData());
    }

    @Test
    public void testGetDetails() {
        String expected = "Client Details: \n"
                + "Name: " + validName + "\n"
                + "Surname: " + validSurname + "\n"
                + "Email: " + validEmail + "\n"
                + "Address: " + validAddress + "\n"
                + "DNI: " + validDni + "\n"
                + "Number: " + validNumber + "\n"
                + "Antiquity: " + validAntiquity + "\n"
                + "Payment Mode: " + validPaymentMode + "\n"
                + "Membership Level: " + validMembershipLevel + "\n"
                + "Registration Date: " + validRegistrationDate;
        assertEquals(expected, validClient.getDetails());
    }

}