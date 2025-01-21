package com.example.softlearning.applicationcore.entity.employee.dtos;

public class EmployeeDTO {
    private final String name, surname, email, address,dni,number,idEmployee,position;
    private final int antiquity;
    private final double salary;

        public EmployeeDTO(String name, String surname, String email, String address, String dni, String number,int antiquity, String idEmployee,
            String position, double salary) {
            
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.address = address;
            this.dni = dni;
            this.number = number;
            this.antiquity = antiquity;
            this.idEmployee = idEmployee;
            this.position = position;
            this.salary = salary;
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

        public String getIdEmployee() {
            return idEmployee;
        }

        public String getPosition() {
            return position;
        }

        public int getAntiquity() {
            return antiquity;
        }

        public double getSalary() {
            return salary;
        }   

    
    

}
