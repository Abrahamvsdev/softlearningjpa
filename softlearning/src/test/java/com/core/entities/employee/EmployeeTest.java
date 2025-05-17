package com.core.entities.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.employee.model.Employee;

public class EmployeeTest {
    private final String validName = "Luis";
    private final String validSurname = "Martínez";
    private final String validEmail = "luis.martinez@example.com";
    private final String validAddress = "Calle Real 10";
    private final String validDni = "23456789A";
    private final String validNumber = "600987654";
    private final int validAntiquity = 4;
    private final String validIdEmployee = "EMP123";
    private final String validPosition = "Manager";
    private final double validSalary = 2500.0;

    private Employee validEmployee;

    @BeforeEach
    public void setUp() throws Exception {
        validEmployee = Employee.getInstance(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity, validIdEmployee, validPosition, validSalary);
    }

    @Test
    public void testEmployeeCreateOk() {
        try {
            Employee e = Employee.getInstance(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity, validIdEmployee, validPosition, validSalary);
            assertNotNull(e);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testEmployeeCreateInvalidIdEmployee() {
        try {
            Employee.getInstance(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity, "", validPosition, validSalary);
            fail("Debería fallar por idEmployee vacío");
        } catch (Exception e) {
            assertEquals("No es posible crear el empleado: \nBad idEmployee: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testEmployeeCreateInvalidPosition() {
        try {
            Employee.getInstance(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity, validIdEmployee, "", validSalary);
            fail("Debería fallar por position vacío");
        } catch (Exception e) {
            assertEquals("No es posible crear el empleado: \nBad position: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testEmployeeCreateInvalidSalary() {
        try {
            Employee.getInstance(validName, validSurname, validEmail, validAddress, validDni, validNumber, validAntiquity, validIdEmployee, validPosition, -100.0);
            fail("Debería fallar por salario negativo");
        } catch (Exception e) {
            assertEquals("No es posible crear el empleado: \nBad salary: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    // ==================== TESTS DE SETTERS ====================
    @Test
    public void testSetIdEmployee() {
        assertEquals(0, validEmployee.setIdEmployee(validIdEmployee));
    }

    @Test
    public void testSetIdEmployeeShort() {
        assertEquals(-3, validEmployee.setIdEmployee("a"));
    }

    @Test
    public void testSetIdEmployeeLong() {
        assertEquals(-7, validEmployee.setIdEmployee("idEmployeeDemasiadoLargoParaUnEmpleado"));
    }

    @Test
    public void testSetIdEmployeeNull() {
        assertEquals(-1, validEmployee.setIdEmployee(null));
    }

    @Test
    public void testSetIdEmployeeEmpty() {
        assertEquals(-2, validEmployee.setIdEmployee(""));
    }

    @Test
    public void testSetPosition() {
        assertEquals(0, validEmployee.setPosition(validPosition));
    }

    @Test
    public void testSetPositionShort() {
        assertEquals(-3, validEmployee.setPosition("a"));
    }

    @Test
    public void testSetPositionLong() {
        assertEquals(-7, validEmployee.setPosition("positionDemasiadoLargaParaUnEmpleado"));
    }

    @Test
    public void testSetPositionNull() {
        assertEquals(-1, validEmployee.setPosition(null));
    }

    @Test
    public void testSetPositionEmpty() {
        assertEquals(-2, validEmployee.setPosition(""));
    }

    @Test
    public void testSetSalary() {
        assertEquals(0, validEmployee.setSalary(validSalary));
    }

    @Test
    public void testSetSalaryNegative() {
        assertEquals(-23, validEmployee.setSalary(-100.0));
    }

    // ==================== TESTS DE GETTERS ====================
    @Test
    public void testGetIdEmployee() {
        assertEquals(validIdEmployee, validEmployee.getIdEmployee());
    }

    @Test
    public void testGetPosition() {
        assertEquals(validPosition, validEmployee.getPosition());
    }

    @Test
    public void testGetSalary() {
        assertEquals(validSalary, validEmployee.getSalary());
    }
}
