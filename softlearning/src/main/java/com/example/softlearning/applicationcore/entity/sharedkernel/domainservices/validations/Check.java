package com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Check {
    //*Si es null retorna -1 */
    public static int isNull(String s) {
        if (s == null) {
            return -1; // -1: El valor es null
        }
        return 0; // 0: El valor no es null
    }

    public static int isEmpty(String s) {
        int e = isNull(s);
        if (e != 0) {
            return e; // -1: El valor es null
        }
        if (s.trim().length() == 0) {
            return -2; // -2: El valor está vacío
        }
        return 0; // 0: El valor no está vacío
    }

    public static int checkEmail(String email) {
        int isEmpty = Check.isEmpty(email);
        if (isEmpty != 0) {
            return isEmpty; // -1: null, -2: vacío
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return -10; // Formato de email incorrecto
        }
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return 0; // Email válido
        } catch (AddressException e) {
            return -10; // Formato de email incorrecto
        }
    }

    public static int checkLength(String s, int min, int max) {
        int e = Check.isEmpty(s);
        if (e != 0) {
            return e; // -1: null, -2: vacío
        }
        if (s.length() < min) {
            return -3; // -3: Menos caracteres de los mínimos permitidos
        }
        if (s.length() > max) {
            return -7; // -7: Más caracteres de los máximos permitidos
        }
        return 0; // 0: Longitud válida
    }

    public static int isTrue(boolean b) {
        if (b == true) {
            return 0; // 0: Es true
        }
        return -12; // -12: Es false
    }

    public static int isFalse(boolean b) {
        if (b == false) {
            return 0; // 0: Es false
        }
        return -13; // -12: Es true
    }

    public static int checkBoolean(boolean b) {
        if (b == true || b == false) {
            return 0; // 0: Está bien
        }
        return 0; // Sea lo que sea que retorne 0
    }

    public static int isValidDate(String date) {
        int isEmpty = Check.isEmpty(date);
        if (isEmpty != 0) {
            return isEmpty; // -1: null, -2: vacío
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(date, formatter);
            return 0; // 0: Fecha válida
        } catch (DateTimeParseException e) {
            return -14; // -14: Formato de fecha incorrecto
        }
    }

    public static int isValidDateComplete(String date) {
        int isEmpty = Check.isEmpty(date);
        if (isEmpty != 0) {
            return isEmpty; // -1: null
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd-HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime.parse(date, formatter);
            return 0; // 0: Fecha válida
        } catch (DateTimeParseException e) {
            return -14; // -14: Formato de fecha incorrecto
        }
    }

    public static int range(double num, double min, double max) {
        if (num < min) {
            return -23; // -23: Número menor que el mínimo
        }
        if (num > max) {
            return -24; // -24: Número mayor que el máximo
        }
        return 0; // 0: Número en rango
    }

    public static int rangeDiscount(double num) {
        int e = range(num, 0, 50);
        if (e != 0) {
            if (e == -24) {
                return -20; // -20: Descuento mayor al 50%
            }
            return e; // -23: menor que 0
        }
        return 0; // 0: Descuento válido
    }

    public static int range(int num, int min, int max) {
        if (num < min) {
            return -23; // -23: Número menor que el mínimo
        }
        if (num > max) {
            return -24; // -24: Número mayor que el máximo
        }
        return 0; // 0: Número en rango
    }

    public static int checkDNI(String dni) {
        int isEmpty = Check.isEmpty(dni);
        if (isEmpty != 0) {
            return isEmpty; // -1: null, -2: vacío
        }

        if (dni.matches("^\\d{8}[A-Z]$")) {
            return 0; // 0: Formato válido
        } else {
            return -9; // -9: Formato de DNI incorrecto
        }

    }

    public static int checkISBN(String isbn) {
        int isEmpty = Check.isEmpty(isbn);
        if (isEmpty != 0) {
            return isEmpty; // -1: null, -2: vacío
        }
        String cleanIsbn = isbn.replaceAll("-", "");
        int error = Check.checkLength(cleanIsbn, 10, 13);
        if (error != 0) {
            return error; // -3: menos de 10, -7: más de 13
        }
        if (cleanIsbn.length() == 13) {
            if (cleanIsbn.matches("^97[89]\\d{10}$")) {
                return 0; // 0: ISBN-13 válido
            } else {
                return -22; // -22: Formato de ISBN incorrecto
            }
        }
        if (cleanIsbn.length() == 10) {
            if (cleanIsbn.matches("^(?:\\d{9}X|\\d{10})$")) {
                return 0; // 0: ISBN-10 válido
            } else {
                return -22; // -22: Formato de ISBN incorrecto
            }
        }
        return -22; // -22: Formato de ISBN incorrecto
    }

    public static int checkMobilePhone(String n) {
        int isEmpty = Check.isEmpty(n);
        if (isEmpty != 0) {
            return isEmpty; 
        }
        if (!n.matches("^[67]\\d{8}$")) {
            return -17; // -17: No es un móvil válido español

        }
        return 0; // 0: Móvil válido
    }

    public static String getErrorMessage(int e) {
        return switch (e) {
            case 0 ->
                "";
            case -1 ->
                "No puede ser null";
            case -2 ->
                "No puede estar vacío";
            case -3 ->
                "Has introducido pocos caracteres";
            case -4 ->
                "Formato de fecha incorrecto";
            case -5 ->
                "El campo introducido es demasiado corto";
            case -6 ->
                "Has introducido un número negativo";
            case -7 ->
                "Has introducido demasiados caracteres";
            case -8 ->
                "La fecha de fin no puede ser menor a la fecha de inicio";
            case -9 ->
                "DNI no válido";
            case -10 ->
                "Formato de email incorrecto";
            case -11 ->
                "Formato de software incorrecto";
            case -12 ->
                "El valor es falso";
            case -13 ->
                "El valor es verdadero";
            case -14 ->
                "Formato de fecha incorrecto";
            case -15 ->
                "La referencia introducida no es válida, debe ser mayor a 1000";
            case -16 ->
                "La referencia introducida no es válida, debe ser menor a 10000";
            case -17 ->
                "El número de teléfono debe ser un número válido de 9 cifras";
            case -18 ->
                "Por favor, introduce correctamente la fragilidad del paquete";
            case -19 ->
                "El Id del orden no ha sido seteado";
            case -20 ->
                "El descuento no puede ser mayor al 50%";
            case -21 ->
                "No hay detalle";
            case -22 ->
                "El ISBN no está formado correctamente";
            case -23 ->
                "El número es más pequeño de lo esperado";
            case -24 ->
                "El número es más grande de lo esperado";
            case -25 ->
                "No puedes introducir fecha de fin sin fecha de inicio";
            default ->
                "No reconocible";
        };
    }
}
