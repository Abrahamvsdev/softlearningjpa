package com.core.entities.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    public void testPersonCreateOk() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity);
            assertNotNull(p);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testPersonCreateInvalidName() {
        try {
            PersonObject p = new PersonObject();
            p.person(null, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por nombre nulo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad name: No puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidSurname() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, null, validEmail, validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por apellido nulo");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad surname: No puede ser null\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidEmail() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, "email-invalido", validAddress, validDni, validNumber, validAntiquity);
            fail("Debería fallar por email inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad email: Formato de email incorrecto\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidAddress() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, "", validDni, validNumber, validAntiquity);
            fail("Debería fallar por dirección vacía");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad address: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidDni() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, "DNI_INVALIDO", validNumber, validAntiquity);
            fail("Debería fallar por DNI inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad dni: DNI no válido\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidNumber() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, validDni, "12", validAntiquity);
            fail("Debería fallar por número corto");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad number: Has introducido pocos caracteres\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidAntiquity() {
        try {
            PersonObject p = new PersonObject();
            p.person(validName, validSurname, validEmail, validAddress, validDni, validNumber, -5);
            fail("Debería fallar por antigüedad negativa");
        } catch (Exception e) {
            assertEquals("No es posible crear al tipo: \nBad antiquity: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    @Test
    public void testPersonCreateInvalidAll() {
        try {
            PersonObject p = new PersonObject();
            p.person("", "A", "email", "", "dni", "1", -5);
            fail("Debería fallar por múltiples errores");
        } catch (Exception e) {
            String expected = "No es posible crear al tipo: \n" +
                "Bad name: No puede estar vacío\n" +
                "Bad surname: Has introducido pocos caracteres\n" +
                "Bad email: Formato de email incorrecto\n" +
                "Bad address: No puede estar vacío\n" +
                "Bad dni: DNI no válido\n" +
                "Bad number: Has introducido pocos caracteres\n" +
                "Bad antiquity: El número es más pequeño de lo esperado\n";
            assertEquals(expected, e.getMessage());
        }
    }

    // ==================== TESTS DE SETTERS ====================

    /* SET NAME */
    @Test
    public void testSetName() {
        assertEquals(0, validPerson.setName(validName));
    }

    @Test
    public void testSetNameShort() {
        assertEquals(-3, validPerson.setName("a"));
    }

    @Test
    public void testSetNameLong() {
        assertEquals(-7, validPerson.setName("nombredemasiadolargounmontondelargoso"));
    }

    @Test
    public void testSetNameNull() {
        assertEquals(-1, validPerson.setName(null));
    }

    @Test
    public void testSetNameEmpty() {
        assertEquals(-2, validPerson.setName(""));
    }

    @Test
    public void testSetNameSpaceString() {
        assertEquals(-2, validPerson.setName("   "));
    }

    /* SET SURNAME */

    @Test
    public void testSetSurname() {
        assertEquals(0, validPerson.setSurname(validSurname));
    }

    
    @Test
    public void testSetSurnameShort() {
        assertEquals(-3, validPerson.setSurname("a"));
    }

    @Test
    public void testSetSurnameLong() {
        assertEquals(-7, validPerson.setSurname("apellidodemasiadolargounmontondelargoso"));
    }

    @Test
    public void testSetSurnameNull() {
        assertEquals(-1, validPerson.setSurname(null));
    }

    @Test
    public void testSetSurnameEmpty() {
        assertEquals(-2, validPerson.setSurname(""));
    }

    @Test
    public void testSetSurnameSpaceString() {
        assertEquals(-2, validPerson.setSurname("   "));
    }

    /* SET EMAIL */

    @Test
    public void testSetEmail() {
        assertEquals(0, validPerson.setEmail(validEmail));
    }

    @Test
    public void testSetEmailMissingAt() {
        assertEquals(-10, validPerson.setEmail("userexample.com"));
    }

    @Test
    public void testSetEmailMultipleAtSymbols() {
        assertEquals(-10, validPerson.setEmail("user@@example.com"));
    }

    @Test
    public void testSetEmailInvalidDomain() {
        assertEquals(-10, validPerson.setEmail("user@.com"));
    }

    @Test
    public void testSetEmailEmptyFront() {
        assertEquals(-10, validPerson.setEmail("@example.com"));
    }

    @Test
    public void testSetEmailNull() {
        assertEquals(-1, validPerson.setEmail(null));
    }

    @Test
    public void testSetEmailEmpty() {
        assertEquals(-2, validPerson.setEmail(""));
    }

    @Test
    public void testSetEmailSpace() {
        assertEquals(-2, validPerson.setEmail("    "));
    }

    /* SET ADDRESS */

    @Test
    public void testSetAddress() {
        assertEquals(0, validPerson.setAddress(validAddress));
    }

    @Test
    public void testSetAddressShort() {
        assertEquals(-3, validPerson.setAddress("a"));
    }

    @Test
    public void testSetAddressLong() {
        assertEquals(-7, validPerson.setAddress("direccióndemasiadolargaunmontondelargosa"));
    }

    @Test
    public void testSetAddressNull() {
        assertEquals(-1, validPerson.setAddress(null));
    }

    @Test
    public void testSetAddressEmpty() {
        assertEquals(-2, validPerson.setAddress(""));
    }

    @Test
    public void testSetAddressSpaceString() {
        assertEquals(-2, validPerson.setAddress("   "));
    }

    /* SET DNI */

    @Test
    public void testSetDni() {
        assertEquals(0, validPerson.setDni(validDni));
    }

    @Test
    public void testSetDniLowerCaseLetter() {
        assertEquals(-9, validPerson.setDni("12345678a"));
    }

    @Test
    public void testSetDniNoLetter() {
        assertEquals(-9, validPerson.setDni("12345678"));
    }

    @Test
    public void testSetDniLong() {
        assertEquals(-9, validPerson.setDni("123456789A"));
    }

    @Test
    public void testSetDniShort() {
        assertEquals(-9, validPerson.setDni("12345A"));
    }

    @Test
    public void testSetDniMixedCharacters() {
        assertEquals(-9, validPerson.setDni("12AB34CD5E"));
    }

    @Test
    public void testSetDniNull() {
        assertEquals(-1, validPerson.setDni(null));
    }

    @Test
    public void testSetDniEmpty() {
        assertEquals(-2, validPerson.setDni(""));
    }

    @Test
    public void testSetDniSpaceString() {
        assertEquals(-2, validPerson.setDni("   "));
    }
    /* SET NUMBER */

    @Test
    public void testSetNumber() {
        assertEquals(0, validPerson.setNumber(validNumber));
    }

    @Test
    public void testSetNumberShort() {
        assertEquals(-3, validPerson.setNumber("a"));
    }

    @Test
    public void testSetNumberLong() {
        assertEquals(-7, validPerson.setNumber("SDGEDSRGERSGERGSSG"));
    }

    @Test
    public void testSetNumberNull() {
        assertEquals(-1, validPerson.setNumber(null));
    }

    @Test
    public void testSetNumberEmpty() {
        assertEquals(-2, validPerson.setNumber(""));
    }

    @Test
    public void testSetNumberSpaceString() {
        assertEquals(-2, validPerson.setNumber("   "));
    }

    /* SET ANTIQUITY */

    @Test
    public void testSetAntiquity() {
        assertEquals(0, validPerson.setAntiquity(validAntiquity));
    }

    @Test
    public void testSetAntiquityNumberZero() {
        assertEquals(0, validPerson.setAntiquity(0));
    }

    @Test
    public void testSetAntiquityNegative() {
        assertEquals(-23, validPerson.setAntiquity(-5));
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



}