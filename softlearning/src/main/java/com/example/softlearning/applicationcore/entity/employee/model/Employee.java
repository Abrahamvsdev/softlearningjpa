package com.example.softlearning.applicationcore.entity.employee.model;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.stakeholders.Person;

public class Employee extends Person {
    protected String idEmployee; // ID no el dni, osea como un numero de targeta o algo
    protected String position;    // Cargo del empleado hacer futuro enum con basico intermedio y jefe
    protected double salary;      // Sueldo

    protected Employee() {
        
    }

    public static Employee getInstance(String name, String surname, String email, String address, String dni, String number, int antiquity,
                                        String idEmployee, String position, double salary) throws Exception {
        Employee employee = new Employee();

        // Validaciones y establecimiento de atributos
        StringBuilder errors = new StringBuilder();
        int errorCode;

        // Llamada al constructor de la clase padre
        employee.person(name, surname, email, address, dni, number, antiquity);

        if ((errorCode = employee.setIdEmployee(idEmployee)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = employee.setPosition(position)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = employee.setSalary(salary)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (errors.length() > 0) {
            throw new Exception("No es posible crear el empleado: \n" + errors.toString());
        }

        return employee;
    }

    // Getters
    public String getIdEmployee() {
        return idEmployee;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }



    // setters
    public int setIdEmployee(String idEmployee) {
        
        int errorCode = Check.minMaxLength(idEmployee,  3, 20);
        if (errorCode == 0) {
            this.idEmployee = idEmployee;
        }
        return errorCode;
    }

    public int setPosition(String position) {
        int errorCode = Check.minMaxLength(position, 3, 20);
        if (errorCode == 0) {
            this.position = position;
        }
        return errorCode;
    }

    public int setSalary(double salary) {
        int errorSalary = Check.range(salary);
        if (errorSalary == 0) {
            this.salary = salary;
        }
        return errorSalary;
    }

    @Override
    public String getContactData() {
        return "Employee: " + getName() + " Surname: " + getSurname() + ", Email: " + getEmail() + ", Position: " + getPosition();
    }

    @Override
    public String getDetails() {
        // Devuelve los detalles del empleado en un block o una cosa asi, 
        //he hecho esto para que sea mas facil de leer, y para quitar el warning
        return """
                Employee Details: 
                Name: """ + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Address: " + getAddress() + "\n" +
                "DNI: " + getDni() + "\n" +
                "Number: " + getNumber() + "\n" +
                "Birthday " + getAntiquity() + "\n" +
                "ID Employee: " + getIdEmployee() + "\n" +
                "Position: " + getPosition() + "\n" +
                "Salary: €" + getSalary();
    }
}

