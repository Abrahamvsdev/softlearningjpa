package com.core.entities.person;


// Clase concreta para testing

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PersonObjectTest {

    // Valores válidos por defecto
    private final String validName = "Juan";
    private final String validSurname = "Pérez";
    private final String validEmail = "juan.perez@example.com";
    private final String validAddress = "Calle Falsa 123";
    private final String validDni = "12345678Z";
    private final String validNumber = "600112233";
    private final int validAntiquity = 5;

    private PersonObject validPerson;

    @BeforeEach
    public void setUp() throws Exception {
        validPerson = new PersonObject();
        validPerson.person(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity);
        
    }

    // ==================== VALIDACIÓN EN CREACIÓN ====================
    // ---------------------- Name ----------------------


    @Test
    public void testInvalidName_Null() {
        try {
            PersonObject p = new PersonObject();
            p.person(null, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por nombre nulo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nNo puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testInvalidName_TooShort() {
        try {
            PersonObject p = new PersonObject();
            p.person("J", validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por nombre corto");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido pocos caracteres\n", e.getMessage());
        }
    }

    // ---------------------- Surname ----------------------
    @Test
    public void testInvalidSurname_TooLong() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, "A".repeat(16), validEmail, validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por apellido largo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido demasiados caracteres\n", e.getMessage());
        }
    }

    // ---------------------- Email ----------------------
    @Test
    public void testInvalidEmail_Format() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, "email-invalido", validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por email inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nFormato de email incorrecto\n", e.getMessage());
        }
    }

    // ---------------------- Address ----------------------
    @Test
    public void testInvalidAddress_Empty() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, "", validDni, validNumber, validAntiquity);
            fail("Debería fallar por dirección vacía");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nNo puede ser null\n", e.getMessage());
        }
    }

    // ---------------------- DNI ----------------------
    @Test
    public void testInvalidDni_Format() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, "DNI_INVALIDO", validNumber, validAntiquity);
            fail("Debería fallar por DNI inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nDNI no válido\n", e.getMessage());
        }
    }

    // ---------------------- Number ----------------------
    @Test
    public void testInvalidNumber_Length() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, validDni, "12", validAntiquity);
            fail("Debería fallar por número corto");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido pocos caracteres\n", e.getMessage());
        }
    }

    // ---------------------- Antiquity ----------------------
    @Test
    public void testInvalidAntiquity_Negative() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, validDni, validNumber, -5);
            fail("Debería fallar por antigüedad negativa");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nHas introducido un numero negativo\n", e.getMessage());
        }
    }

    // ==================== TESTS DE SETTERS ====================
    // ---------------------- setName ----------------------
    @Test
    public void testSetName_Valid() {
        assertEquals(0, validPerson.setName("Pedro"));
        assertEquals("Pedro", validPerson.getName());
    }

    // ---------------------- setSurname ----------------------
    @Test
    public void testSetSurname_Invalid() {
        assertEquals(-7, validPerson.setSurname("A".repeat(16)));
        assertEquals(validSurname, validPerson.getSurname()); // Mantiene valor original
    }

    // ---------------------- setEmail ----------------------
    @Test
    public void testSetEmail_Valid() {
        assertEquals(0, validPerson.setEmail("nuevo@email.com"));
        assertEquals("nuevo@email.com", validPerson.getEmail());
    }

    // ---------------------- setAddress ----------------------
    @Test
    public void testSetAddress_EdgeCase() {
        assertEquals(0, validPerson.setAddress("A".repeat(15))); // Longitud máxima
        assertEquals("A".repeat(15), validPerson.getAddress());
    }

    // ---------------------- setDni ----------------------
    @Test
    public void testSetDni_Valid() {
        assertEquals(0, validPerson.setDni("87654321X"));
        assertEquals("87654321X", validPerson.getDni());
    }

    // ---------------------- setNumber ----------------------
    @Test
    public void testSetNumber_Invalid() {
        assertEquals(-3, validPerson.setNumber("AB")); 
        assertEquals(validNumber, validPerson.getNumber());
    }
    @Test
    public void testSetNumber_empty() {
        assertEquals(-1, validPerson.setNumber("  ")); 
        assertEquals(validNumber, validPerson.getNumber());
    }

    // ---------------------- setAntiquity ----------------------
    @Test
    public void testSetAntiquity_Zero() {
        assertEquals(0, validPerson.setAntiquity(0));
        assertEquals(0, validPerson.getAntiquity());
    }

    // ==================== TESTS DE GETTERS ====================
    @Test
    public void testGetName() {
        assertEquals(validName, validPerson.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals(validSurname, validPerson.getSurname());
    }

    @Test
    public void testGetEmail() {
        assertEquals(validEmail, validPerson.getEmail());
    }

    @Test
    public void testGetAddress() {
        assertEquals(validAddress, validPerson.getAddress());
    }

    @Test
    public void testGetDni() {
        assertEquals(validDni, validPerson.getDni());
    }

    @Test
    public void testGetNumber() {
        assertEquals(validNumber, validPerson.getNumber());
    }

    @Test
    public void testGetAntiquity() {
        assertEquals(validAntiquity, validPerson.getAntiquity());
    }

    // ==================== MÉTODOS HEREDADOS ====================
    @Test
    public void testGetDetails() {
        String expected = "Name: Juan, Surname: Pérez, Email: juan.perez@example.com, " +
                          "Address: Calle Falsa 123, DNI: 12345678Z, Number: 600112233, Antiquity: 5";
        assertEquals(expected, validPerson.getDetails());
    }

    @Test
    public void testGetContactData() {
        assertEquals("Name: pepito", validPerson.getContactData());
    }

    // ==================== CASOS MÚLTIPLES ====================
    @Test
    public void testMultipleErrors() {
        try {
            PersonObject p = new PersonObject();
            p.person("", "A", "email", "", "dni", "1", -5);
            fail("Debería fallar por múltiples errores");
        } catch (Exception e) {
            String expected = "No es posible crear al tipo: \n" +
                "No puede ser null\n" +
                "Has introducido pocos caracteres\n" +
                "Formato de email incorrecto\n" +
                "No puede ser null\n" +
                "DNI no válido\n" +
                "Has introducido pocos caracteres\n" +
                "Has introducido un numero negativo\n";
            assertEquals(expected, e.getMessage());
        }
    }
}