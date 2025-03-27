package com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    // SI no es null da 0
    public static int isNull(String s) {
        // Esta función comprueba si el string es nulo o vacío
        if (s == null) { // Verificamos primero si s es null, porque 
            //si hacemos trim a un null dara un error
            return -1;
        }
        s = s.trim(); // Ahora aplicamos trim() solo si s no es null para que no de el error
        if (s.length() == 0 ) { // Luego verificamos si está vacío
            return -1;
        } else {
            // Si no es nulo ni vacío, devolvemos 0
            return 0;
        }
    }

    public static int checkEmail(String email) {
        String pattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        /*
         * Los caracteres permitidos antes del símbolo @ son letras minúsculas y mayúsculas (a-z, A-Z), números (0-9), punto (.), guión bajo (_) y guión (-).
         * Debe haber al menos uno de estos caracteres.
         * Símbolo @: Debe haber exactamente un símbolo @.
         * Caracteres permitidos después de @: Después del símbolo @, los caracteres permitidos son letras minúsculas y mayúsculas (a-z, A-Z), números (0-9), punto (.) y guión (-).
         * Debe haber al menos uno de estos caracteres.
         * Punto y dominio: Después de los caracteres permitidos, debe haber un punto (.) seguido de entre 2 y 6 letras (a-z, A-Z). Esto representa el dominio, como .com, .net, .org, etc.
         * OLE MIS COJONES
         */
        if (email == null) {
            return -1;
        }

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);

        if (m.matches()) {
            return 0;
        } else {
            return -10;
        }
    }

    public static int minLength(String s) {
        //esta funcion comprueba si el string no esta vacio
        // y si ademas es mas largo que 3 caracteres

        //en java no hace falta poner los "::", si quiero llamar a un metodo seria 
        //Check.isnull(s)
        if (Check.isNull(s) == 0) {
            if (s.length() < 3) {
                return -3;
            } else {
                return 0;
            }
        } else {
            return -1;
        }

    }

    //esto checkea si es verdadero -12 que si, 0 que no
    public static int checkBoolean(boolean b) {
        if (b == true) {
            return -12;
        }
        return 0;
    }

    public static int maxLength(String s) {
        //esta funcion comprueba si el string no esta vacio
        // y si ademas es mas largo que 15 caracteres
        //en java no hace falta poner los "::", si quiero llamar a un metodo seria
        if (Check.isNull(s) == 0) {

            if (s.length() > 15) {
                return -7;
            } else {
                return 0;
            }
        } else {
            return -1;
        }

    }

    public static int minMaxLength(String s) {
        //esta funcion comprueba si el length es mayor de 3 y menos de 15
        int resultado = Check.isNull(s);
        if (resultado == 0) {
            if (s.length() < 3) {
                return -3;
            }
            if (s.length() > 15) {
                return -7;
            }
        }
        return resultado;
    }

    // public static int minMaxLength(String s, int min, int max){  
    //     // Esto se llama parametrizar, hacerlas reusables entrando por parametro de donde a donde quiero medir
    //     int resultado = Check.isNull(s);
    //     s = s.trim();
    //     if(resultado==0){
    //         if(s.length()<min)
    //             return -3;
    //         if (s.length()>max)
    //             return -7;
    //     }
    //     return resultado;
    // }
    // a pasos:
    //se declara el patron
    // se compila
    //se usa el matcher
    // si es correcto local date con el "formatter of pattern"
    //
    /**
     * Valida si una cadena representa una fecha válida en formato dd-MM-yyyy.
     *
     * @param date La cadena a validar
     * @return 0 si la fecha es válida, código de error negativo en caso
     * contrario
     */
    public static int isValidDate(String date) {
        // Expresión regular para validar el formato de la fecha (dd-MM-yyyy)
        int isNull = Check.isNull(date);
        if (isNull != 0) {
            return isNull;  // Código de error para null
        }
        
        // String datePattern = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$";

        // // Compila
        // Pattern pattern = Pattern.compile(datePattern);

        // // matcher
        // Matcher matcher = pattern.matcher(date);

        // if (matcher.matches()) {
            // si es correcto, formatter pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            
            try {
                // si la fecha es valida, no hay errores
                LocalDate.parse(date, formatter);
                return 0;  // si esta bien
            } catch (DateTimeParseException e) {
                // Si ocurre un error al intentar parsear la fecha, significa que no es válida
                return -14;  //formato correcto pero no valida
            }
        // } else {
            // Si no coincide con el patrón de formato
            //return -4;  // Código de error para un formato incorrecto
       // }
    }

    ///////////////


public static int isValidDateComplete(String date) {
    int isNull = Check.isNull(date);
    if (isNull != 0) {
        return isNull;  // Código de error para null
    }

        if (date.length() > 0) {
            // Pattern pattern = Pattern.compile("^(\\d{4})/(\\d{2})/(\\d{2})-(\\d{2}):(\\d{2}):(\\d{2})$");
            // Matcher matcher = pattern.matcher(date);
            //if (matcher.matches()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd-HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDateTime.parse(date, formatter);
                    return 0;
                } catch (DateTimeParseException e) {
                    return -14;  // Formato correcto pero fecha inválida
                }
            // } else {
            //     return -4;  // Formato incorrecto
            // }
        }
        return -1;  // Fecha nula
    }

    // public static int isValidDate(String stringdate) {
    //     // string del chat gpt que no me convence
    //     String datePattern = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$";
    //     // se compila
    //     Pattern pattern = Pattern.compile(datePattern);
    //     // comprobamos si es un null, pero al comenzar por mayus es un objeto y no se si puede ser null, preguntar jose
    //     if (isNull(stringdate) != 0) {
    //         return -1;
    //     }
    //     // el matcher
    //     Matcher matcher = pattern.matcher(stringdate);
    //     // y si hace match
    //     if(matcher.matches()){
    //         return 0;
    //     }else{
    //         return -4;
    //     }
    // }
    //RANGE PARA DOBLE
    public static int range(double e) {
        //esta funcion coprueba si el double es nulo o positivo
        if (e < 0) {
            return -6;
        } else {
            return 0;
        }
    }

    //RANGE PARA DESCUENTO
    public static int rangeDiscount(double e) {
        //esta funcion coprueba si el descuento en double esta correcto, y no puede ser mayor que 50
        if (e < 0.00) {
            return -6;
        } else if (e > 50.00) {
            return -20;
        } else {
            return 0;
        }
    }

    //RANGE PARA INT
    public static int range(int e) {
        //esta funcion coprueba si el int es nulo o positivo
        if (e < 0) {
            return -6;
        } else {
            return 0;
        }
    }

    public static int checkDNI(String dni) {
        // Patron para un DNI: 8 digitos seguidos de 1 letra mayuscula
        if(dni == null){
            return -1;
        }

        String pattern = "^\\d{8}[A-Z]$";

        // Compila
        Pattern r = Pattern.compile(pattern);

        // matchea
        Matcher m = r.matcher(dni);

        // Si matchea vale, si no -9
        if (m.matches()) {
            return 0;  // DNI valido
        } else {
            return -9;  // DNI invalido
        }
    }

    //este validate para double
    public static int validate(double price) {
        //esta funcion comprueba si el double es negativo o positivo
        if (price < 0) {
            return -6;
        } else {
            return 0;
        }
    }

    public static int checkISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return -1;  // Código de error para null o vacío
        }

        // Eliminar guiones
        String cleanIsbn = isbn.replaceAll("-", "");

        // Verificar longitud mínima y máxima
        if (cleanIsbn.length() < 10) {
            return -3;
        }
        if (cleanIsbn.length() > 13) {
            return -8;
        }

        // Validar ISBN-13
        if (cleanIsbn.length() == 13) {
            if (cleanIsbn.matches("^97[89]\\d{10}$")) {
                return 0;  // ISBN-13 válido
            } else {
                return -22;  // ISBN-13 inválido
            }
        }

        // Validar ISBN-10
        if (cleanIsbn.length() == 10) {
            if (cleanIsbn.matches("^(?:\\d{9}X|\\d{10})$")) {
                return 0;  // ISBN-10 válido
            } else {
                return -22;  // ISBN-10 inválido
            }
        }

        return -22; // Si no es ni ISBN-10 ni ISBN-13
    }

    public static int checkMobilePhone(String n) {
        //esta funcion comprueba si el string es un numero valido de 9 cifras
        if (Check.isNull(n) == 0) {
            if (n.length() != 9 || !n.matches("^[67]\\d{8}$")) {
                return -17;
            }
        }
        return 0;
    }

    public static String getErrorMessage(int e) {
        return switch (e) {
            case 0 ->
                "";
            case -1 ->
                "No puede ser null";
            case -2 ->
                "No puede estar vacio";
            case -3 ->
                "Has introducido pocos caracteres";
            case -4 ->
                "Formato de fecha incorrecto";
            case -5 ->
                "El campo introducido es demasiado corto";
            case -6 ->
                "Has introducido un numero negativo";
            case -7 ->
                "Has introducido demasiados caracteres";
            case -8 ->
                "Isbn no valido, introduce una cifra de 13 dígitos válido";
            case -9 ->
                "DNI no válido";
            case -10 ->
                "Formato de email incorrecto";
            case -11 ->
                "Formato de Software incorrecto";
            case -12 ->
                "El pago está atrasado";
            case -13 ->
                "El pago está correctamente";
            case -14 ->
                "Formato correcto pero no válida";
            case -15 ->
                "La referencia introducida no es válida, debe ser mayor a 1000";
            case -16 ->
                "La referencia introducida no es válida, debe ser menor a 10000";
            case -17 ->
                "el numero de teléfono debe ser un numero valido de 9 cifras";
            case -18 ->
                "Por favor, introduce correctamente la fragilidad del paquete";
            case -19 ->
                "el Id del Orden no ha sido seteado";
            case -20 ->
                "El descuento no puede ser mayor al 50% ";
            case -21 ->
                "No hay detalle";
            case -22 ->
                "El ISBN no esta formado correctamente";
            default ->
                "No reconocible";
        };

    }
}
