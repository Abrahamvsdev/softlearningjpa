package com.core.entities.client;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.client.model.Client;


public class ClientTestJunit {
    
    private DateTimeFormatter formatter;
    private String validName;
    private String validSurname;
    private String validEmail;
    private String validAddress;
    private String validDni;
    private String validNumber;
    private String validPaymentMode;
    private int validAntiquity;
    private String validMembershipLevel;
    private String validRegistrationDate;
    
    @BeforeEach // Is BeforeEach as Jose does said
    public void setUp() {
        // Initialize the formatter and valid test data
        formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        validName = "John";
        validSurname = "Doe";
        validEmail = "elputojohn.doe@example.com";
        validAddress = "123 False St";
        validDni = "12345678A";
        validNumber = "123456789";
        validPaymentMode = "Credit Card";
        validAntiquity = 5;
        validMembershipLevel = "Premium";
        validRegistrationDate = "01-01-2023";
    }
    
    @Test
    public void testValidClientCreation() {
        try {
            // Create a valid client
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            // Verify all fields are set correctly
            assertEquals(validName, client.getName());
            assertEquals(validSurname, client.getSurname());
            assertEquals(validEmail, client.getEmail());
            assertEquals(validAddress, client.getAddress());
            assertEquals(validDni, client.getDni());
            assertEquals(validNumber, client.getNumber());
            assertEquals(validAntiquity, client.getAntiquity());
            assertEquals(validPaymentMode, client.getPaymentMode());
            assertEquals(validMembershipLevel, client.getMembershipLevel());
            assertEquals(validRegistrationDate, client.getRegistrationDate());
        } catch (Exception e) {
            fail("Valid client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testInvalidPaymentMode() {
        try {
            // Try to create a client with an empty payment mode
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, "", validAntiquity, validMembershipLevel, validRegistrationDate
            );
            fail("Should have thrown an exception for invalid payment mode");
        } catch (Exception e) {
            // Expected exception
            assertTrue(e.getMessage().contains("No es posible crear el cliente"));
        }
    }
    
    @Test
    public void testInvalidMembershipLevel() {
        try {
            // Try to create a client with an empty membership level
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, "", validRegistrationDate
            );
            fail("Should have thrown an exception for invalid membership level");
        } catch (Exception e) {
            // Expected exception
            assertTrue(e.getMessage().contains("No es posible crear el cliente"));
        }
    }
    
    @Test
    public void testInvalidRegistrationDate() {
        try {
            // Try to create a client with an invalid date format
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, "2023/01/01"
            );
            fail("Should have thrown an exception for invalid registration date");
        } catch (Exception e) {
            // Expected exception
            assertTrue(e.getMessage().contains("No es posible crear el cliente"));
        }
    }
    
    
    @Test
    public void testSetPaymentMode() {
        try {
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            // Test valid payment mode update
            int result = client.setPaymentMode("Debit Card");
            assertEquals(0, result);
            assertEquals("Debit Card", client.getPaymentMode());
            
            // Test invalid payment mode update
            result = client.setPaymentMode("");
            assertNotEquals(0, result);
            assertEquals("Debit Card", client.getPaymentMode()); // Should remain unchanged
        } catch (Exception e) {
            fail("Client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testSetMembershipLevel() {
        try {
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            // Test valid membership level update
            int result = client.setMembershipLevel("Gold");
            assertEquals(0, result);
            assertEquals("Gold", client.getMembershipLevel());
            
            // Test invalid membership level update
            result = client.setMembershipLevel("");
            assertNotEquals(0, result);
            assertEquals("Gold", client.getMembershipLevel()); // Should remain unchanged
        } catch (Exception e) {
            fail("Client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testSetRegistrationDate() {
        try {
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            // Test valid registration date update
            String newDate = "15-06-2022";
            int result = client.setRegistrationDate(newDate);
            assertEquals(0, result);
            assertEquals(newDate, client.getRegistrationDate());
            
            // Test invalid registration date update
            result = client.setRegistrationDate("2022/06/15");
            assertNotEquals(0, result);
            assertEquals(newDate, client.getRegistrationDate()); // Should remain unchanged
        } catch (Exception e) {
            fail("Client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetContactData() {
        try {
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            String expectedContactData = "Client: John Doe, Email: elputojohn.doe@example.com, Payment Mode: Credit Card";
            assertEquals(expectedContactData, client.getContactData());
        } catch (Exception e) {
            fail("Client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetDetails() {
        try {
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, validPaymentMode, validAntiquity, validMembershipLevel, validRegistrationDate
            );
            
            String details = client.getDetails();
            
            // Verify all fields are included in the details
            assertTrue(details.contains("Name: " + validName));
            assertTrue(details.contains("Surname: " + validSurname));
            assertTrue(details.contains("Email: " + validEmail));
            assertTrue(details.contains("Address: " + validAddress));
            assertTrue(details.contains("DNI: " + validDni));
            assertTrue(details.contains("Number: " + validNumber));
            assertTrue(details.contains("Antiquity: " + validAntiquity));
            assertTrue(details.contains("Payment Mode: " + validPaymentMode));
            assertTrue(details.contains("Membership Level: " + validMembershipLevel));
            assertTrue(details.contains("Registration Date: " + validRegistrationDate));
        } catch (Exception e) {
            fail("Client creation threw an exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testMultipleErrors() {
        try {
            // Try to create a client with multiple invalid fields
            Client client = Client.getInstance(
                validName, validSurname, validEmail, validAddress, validDni, 
                validNumber, "", validAntiquity, "", "invalid-date"
            );
            fail("Should have thrown an exception for multiple invalid fields");
        } catch (Exception e) {
            // Expected exception
            String errorMessage = e.getMessage();
            assertTrue(errorMessage.contains("No es posible crear el cliente"));
            // Check that the error message contains multiple error lines
            int lineCount = errorMessage.split("\n").length;
            assertTrue(lineCount > 2, "Error message should contain multiple lines: " + errorMessage);
        }
    }
}
