package com.core.entities.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class OperationObjectTest {

    private final String validDescription = "Compra de materiales";
    private final String validInitDate = "2025/05/19-10:00:00";
    private final String validFinishDate = "2025/05/20-10:00:00";
    private final int validReference = 1234;

    private OperationObject validOperation;

    @BeforeEach
    public void setUp() throws Exception {
        validOperation = new OperationObject();
        validOperation.operation(validReference, validDescription, validInitDate);
    }


    // ==================== VALIDACIÓN EN CREACIÓN ====================

      
    @Test
    public void testOperationCreateOk() {
        try {
            OperationObject op = new OperationObject();
            op.operation(validReference, validDescription, validInitDate);
            assertNotNull(op);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testOperationCreateInvalidReference() {
        try {
            OperationObject op = new OperationObject();
            op.operation(999, validDescription, validInitDate);
            fail("Debería fallar por referencia < 1000");
        } catch (Exception e) {
            assertEquals("No es posible crear la operación: \nBad reference: La referencia introducida no es válida, debe ser mayor a 1000\n", e.getMessage());
        }
    }

    @Test
    public void testOperationDescriptionNullOk() {
        try {
            OperationObject op = new OperationObject();
            op.operation(validReference, null, validInitDate);
            assertNotNull(op);
        } catch (Exception e) {
            fail("No debería fallar description null");
        }
    }

    @Test
    public void testOperationCreateInvalidInitDate() {
        try {
            OperationObject op = new OperationObject();
            op.operation(validReference, validDescription, "fecha-invalida");
            fail("Debería fallar por formato de initDate inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la operación: \nBad initDate: Formato de fecha incorrecto\n", e.getMessage());
        }
    }


    @Test
    public void testOperationCreateMultipleErrors() {
    try {
        OperationObject op = new OperationObject();
        op.operation(999, validDescription, "malafecha");
        fail("Debería fallar con múltiples errores");
    } catch (Exception e) {
        String expected = "No es posible crear la operación: \n" +
                          "Bad reference: La referencia introducida no es válida, debe ser mayor a 1000\n" +
                          "Bad initDate: Formato de fecha incorrecto\n";
        assertEquals(expected, e.getMessage());
    }
}

    // ==================== TESTS DE GETTERS ====================

    @Test
    public void testGetReference() {
        assertEquals(validReference, validOperation.getRef());
    }

    @Test
    public void testGetDescription() {
        assertEquals(validDescription, validOperation.getDescription());
    }

    @Test
    public void testGetInitDate() {
        assertEquals(validInitDate, validOperation.getInitDate());
    }

    @Test
    public void testGetFinishDate() {
        validOperation.setFinishDate(validFinishDate);
        assertEquals(validFinishDate, validOperation.getFinishDate());
    }

    // ==================== TESTS DE SETTERS ====================

    /* SET REF */

    @Test
    public void testSetRefValid() {
        assertEquals(0, validOperation.setRef(1500));
    }

    @Test
    public void testSetRefTooLow() {
        assertEquals(-15, validOperation.setRef(999));
    }

    @Test
    public void testSetRefTooHigh() {
        assertEquals(-16, validOperation.setRef(10001));
    }

    /* SET DESCRIPTION */
    @Test
    public void testSetDescriptionValid() {
        assertEquals(0, validOperation.setDescription("Compra urgente"));
    }

    @Test
    public void testSetDescriptionNull() {
        assertEquals(0, validOperation.setDescription(null));
    }

    @Test
    public void testSetDescriptionEmpty() {
        assertEquals(0, validOperation.setDescription(""));
    }

    @Test
    public void testSetDescriptionWithSpace() {
        assertEquals(0, validOperation.setDescription("   "));
    }

    /* SET INIT DATE */
    @Test
    public void testSetInitDateValid() {
        assertEquals(0, validOperation.setInitDate("2025/06/01-00:00:00"));
    }

    @Test
    public void testSetInitDateLeapYearValid() {
        assertEquals(0, validOperation.setInitDate("2024/02/29-23:59:59")); 
    }

    @Test
    public void testSetInitDateInvalidDate_Feb30() {
        assertEquals(-14, validOperation.setInitDate("2023/02/30-12:00:00")); 
    }

    @Test
    public void testSetInitDateLeapYearInvalid() {
        assertEquals(-14, validOperation.setInitDate("2023/02/29-12:00:00"));
    }

    @Test
    public void testSetInitDateDayZero() {
        assertEquals(-14, validOperation.setInitDate("2023/12/00-12:00:00"));
    }

    @Test
    public void testSetInitDateMonthZero() {
        assertEquals(-14, validOperation.setInitDate("2023/00/15-12:00:00"));
    }

    @Test
    public void testSetInitDateDayTooHigh() {
        assertEquals(-14, validOperation.setInitDate("2023/01/32-12:00:00"));
    }

    @Test
    public void testSetInitDateMonthTooHigh() {
        assertEquals(-14, validOperation.setInitDate("2023/13/15-12:00:00"));
    }

    @Test
    public void testSetInitDateInvalidHour() {
        assertEquals(-14, validOperation.setInitDate("2023/08/15-25:00:00"));    
    }

    @Test
    public void testSetInitDateInvalidMinutes() {
        assertEquals(-14, validOperation.setInitDate("2023/08/15-12:60:00")); 
    }

    @Test
    public void testSetInitDateInvalidSeconds() {
        assertEquals(-14, validOperation.setInitDate("2023/08/15-12:00:60")); 
    }

    @Test
    public void testSetInitDateNull() {
        assertEquals(-1, validOperation.setInitDate(null));
    }

    @Test
    public void testSetInitDateEmpty() {
        assertEquals(-2, validOperation.setInitDate(""));
    }

    @Test
    public void testSetInitDateWithSpace() {
        assertEquals(-2, validOperation.setInitDate("   "));
    }

    /* SET FINISH DATE */

    @Test
    public void testSetFinishDateValid() {
        assertEquals(0, validOperation.setFinishDate("2025/06/01-12:00:00"));
    }

    @Test
    public void testSetFinishDateLeapYearValid() {
        assertEquals(0, validOperation.setFinishDate("2025/06/01-00:00:00"));
    }

    @Test
    public void testSetFinishDateInvalidDate_Feb30() {
        assertEquals(-14, validOperation.setFinishDate("2023/02/30-12:00:00"));
    }

    @Test
    public void testSetFinishDateLeapYearInvalid() {
        assertEquals(-14, validOperation.setFinishDate("2023/02/29-12:00:00"));
    }

    @Test
    public void testSetFinishDateDayZero() {
        assertEquals(-14, validOperation.setFinishDate("2023/12/00-12:00:00"));
    }

    @Test
    public void testSetFinishDateMonthZero() {
        assertEquals(-14, validOperation.setFinishDate("2023/00/15-12:00:00"));
    }

    @Test
    public void testSetFinishDateDayTooHigh() {
        assertEquals(-14, validOperation.setFinishDate("2023/01/32-12:00:00"));
    }

    @Test
    public void testSetFinishDateMonthTooHigh() {
        assertEquals(-14, validOperation.setFinishDate("2023/13/15-12:00:00"));
    }

    @Test
    public void testSetFinishDateInvalidHour() {
        assertEquals(-14, validOperation.setFinishDate("2023/08/15-25:00:00"));
    }

    @Test
    public void testSetFinishDateInvalidMinutes() {
        assertEquals(-14, validOperation.setFinishDate("2023/08/15-12:60:00"));
    }

    @Test
    public void testSetFinishDateInvalidSeconds() {
        assertEquals(-14, validOperation.setFinishDate("2023/08/15-12:00:60"));
    }

    @Test
    public void testSetFinishDateNull() {
        assertEquals(-1, validOperation.setFinishDate(null));
    }

    @Test
    public void testSetFinishDateEmpty() {
        assertEquals(-2, validOperation.setFinishDate(""));
    }

    @Test
    public void testSetFinishDateWithSpace() {
        assertEquals(-2, validOperation.setFinishDate("   "));
    }

    @Test
    public void testSetFinishDateBeforeInitDate() {
        assertEquals(-8, validOperation.setFinishDate("2025/05/01-10:00:00")); 
    }

}
