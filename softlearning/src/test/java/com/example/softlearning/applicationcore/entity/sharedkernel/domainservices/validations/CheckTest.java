package com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CheckTest {

    /* TEST IS NULL */
    @Test
    void testIsNull() {
        assertEquals(0, Check.isNull("Hola"));
    }

    @Test
    void testIsNullEmpty() {
        assertEquals(-1, Check.isNull(""));
    }

    @Test
    void testIsNullSpace() {
        assertEquals(-1, Check.isNull("  "));
    }

    @Test
    void testIsNullNull() {
        assertEquals(-1, Check.isNull(null));
    }

    /* TEST CHECK EMAIL */
    @Test
    void testCheckEmail() {
        assertEquals(0, Check.checkEmail("checkEmail@gmail.com"));
    }

    @Test
    void testCheckEmailWithDots() {
        assertEquals(0, Check.checkEmail("user.name@example.com"));
    }

    @Test
    void testCheckEmailWithSubdomain() {
        assertEquals(0, Check.checkEmail("user@mail.example.com"));
    }

    @Test
    void testCheckEmailNull() {
        assertEquals(-1, Check.checkEmail(null));
    }

    @Test
    void testCheckEmailNoAtSymbol() {
        assertEquals(-10, Check.checkEmail("invalidemail.com"));
    }

    @Test
    void testCheckEmailNoDomain() {
        assertEquals(-10, Check.checkEmail("user@.com"));
    }

    @Test
    void testCheckEmailInvalidDomain() {
        assertEquals(-10, Check.checkEmail("user@example.c"));
    }

    @Test
    void testCheckEmailWithSpace() {
        assertEquals(-10, Check.checkEmail("user name@example.com"));
    }

    @Test
    void testCheckEmailMultipleAtSymbols() {
        assertEquals(-10, Check.checkEmail("user@@example.com"));
    }

    @Test
    void testCheckEmailSpecialCharacters() {
        assertEquals(-10, Check.checkEmail("user!@example.com"));
    }

    /* TEST MIN LENGTH */

    @Test
    void testMinLength() {
        assertEquals(0, Check.minLength("abc"));
    }

    @Test
    void testMinLengthLessThanThree() {
        assertEquals(-3, Check.minLength("ab"));
    }

    @Test
    void testMinLengthEmptyString() {
        assertEquals(-1, Check.minLength(""));
    }

    @Test
    void testMinLengthSpaceString() {
        assertEquals(-1, Check.minLength("   "));
    }

    @Test
    void testMinLengthNullString() {
        assertEquals(-1, Check.minLength(null));
    }

    /* TEST CHECK BOOLEAN */
    @Test
    void testCheckBooleanTrue() {
        assertEquals(-12, Check.checkBoolean(true));
    }

    @Test
    void testCheckBooleanFalse() {
        assertEquals(0, Check.checkBoolean(false));
    }

    /* TEST MAX LENGTH */

    @Test
    void testMaxLengthValid() {
        assertEquals(0, Check.maxLength("123456789012345"));
    }

    @Test
    void testMaxLengthMoreCharacters() {
        assertEquals(-7, Check.maxLength("123-4567890123-456"));
    }

    @Test
    void testMaxLengthEmptyString() {
        assertEquals(-1, Check.maxLength(""));
    }

    @Test
    void testMaxLengthSpaceString() {
        assertEquals(-1, Check.maxLength("           "));
    }

    @Test
    void testMaxLengthNullString() {
        assertEquals(-1, Check.maxLength(null));
    }

    /* TEST MIN MAX LENGTH */

    @Test
    void testMinMaxLengthValid() {
        assertEquals(0, Check.minMaxLength("validLength"));
    }

    @Test
    void testMinMaxLengthShort() {
        assertEquals(-3, Check.minMaxLength("ab"));
    }

    @Test
    void testMinMaxLengthLong() {
        assertEquals(-7, Check.minMaxLength("123-4567890123-456"));
    }

    @Test
    void testMinMaxLengthEmptyString() {
        assertEquals(-1, Check.minMaxLength(""));
    }

    @Test
    void testMinMaxLengthSpaceString() {
        assertEquals(-1, Check.minMaxLength("           "));
    }

    @Test
    void testMinMaxLengthNullString() {
        assertEquals(-1, Check.minMaxLength(null));
    }

    /* TEST IS VALID DATE */

    @Test
    void testIsValidDateCorrectFormatValidDate() {
        assertEquals(0, Check.isValidDate("15-08-2023"));
    }

    @Test
    void testIsValidDateCorrectFormatInvalidDate() {
        assertEquals(-14, Check.isValidDate("30-02-2023")); // Febrero no tiene 30 días
    }

    @Test
    void testIsValidDateIncorrectFormat() {
        assertEquals(-14, Check.isValidDate("2023-08-15")); // Formato incorrecto (yyyy-MM-dd en lugar de dd-MM-yyyy)
    }

    @Test
    void testIsValidDateWithLetters() {
        assertEquals(-14, Check.isValidDate("aa-bb-cccc")); // Formato inválido
    }

    @Test
    void testIsValidDateWithAnotherCharacters() {
        assertEquals(-14, Check.isValidDate("15/08/2023")); // Usa "/" en lugar de "-"
    }

    @Test
    void testIsValidDateNull() {
        assertEquals(-1, Check.isValidDate(null)); // Manejo de fecha nula
    }

    @Test
    void testIsValidDateEmptyString() {
        assertEquals(-1, Check.isValidDate("")); // Cadena vacía no es válida
    }

    @Test
    void testIsValidDateLeapYearValid() {
        assertEquals(0, Check.isValidDate("29-02-2024")); // Año bisiesto, fecha válida
    }

    @Test
    void testIsValidDateLeapYearInvalid() {
        assertEquals(-14, Check.isValidDate("29-02-2023")); // No es bisiesto, 29 de febrero no existe
    }

    @Test
    void testIsValidDateDayZero() {
        assertEquals(-14, Check.isValidDate("00-12-2023")); // Día 00 no es válido
    }

    @Test
    void testIsValidDateMonthZero() {
        assertEquals(-14, Check.isValidDate("15-00-2023")); // Mes 00 no es válido
    }

    @Test
    void testIsValidDateDayTooHigh() {
        assertEquals(-14, Check.isValidDate("32-01-2023")); // Día 32 no existe
    }

    @Test
    void testIsValidDateMonthTooHigh() {
        assertEquals(-14, Check.isValidDate("15-13-2023")); // Mes 13 no existe
    }

    /* TEST IS VALID DATE COMPLETE */
    @Test
    void testIsValidDateCompleteCorrectFormatValidDate() {
        assertEquals(0, Check.isValidDateComplete("2023/08/15-12:30:45"));
    }

    @Test
    void testIsValidDateCompleteCorrectFormatInvalidDate() {
        assertEquals(-14, Check.isValidDateComplete("2023/02/30-12:30:45")); // Febrero no tiene 30 días
    }

    @Test
    void testIsValidDateCompleteIncorrectFormat() {
        assertEquals(-14, Check.isValidDateComplete("15-08-2023 12:30:45")); // Formato incorrecto (dd-MM-yyyy HH:mm:ss
                                                                             // en vez de yyyy/MM/dd-HH:mm:ss)
    }

    @Test
    void testIsValidDateCompleteWithLetters() {
        assertEquals(-14, Check.isValidDateComplete("aaaa/bb/cc-12:30:45")); // Formato inválido
    }

    @Test
    void testIsValidDateCompleteWithExtraCharacters() {
        assertEquals(-14, Check.isValidDateComplete("2023-08-15T12:30:45")); // Usa "T" en lugar de "-"
    }

    @Test
    void testIsValidDateCompleteNull() {
        assertEquals(-1, Check.isValidDateComplete(null)); // Manejo de fecha nula
    }

    @Test
    void testIsValidDateCompleteEmptyString() {
        assertEquals(-1, Check.isValidDateComplete("")); // Cadena vacía no es válida
    }

    @Test
    void testIsValidDateCompleteLeapYearValid() {
        assertEquals(0, Check.isValidDateComplete("2024/02/29-23:59:59")); // Año bisiesto, fecha válida
    }

    @Test
    void testIsValidDateCompleteLeapYearInvalid() {
        assertEquals(-14, Check.isValidDateComplete("2023/02/29-12:00:00")); // No es bisiesto, 29 de febrero no existe
    }

    @Test
    void testIsValidDateCompleteDayZero() {
        assertEquals(-14, Check.isValidDateComplete("2023/12/00-12:00:00")); // Día 00 no es válido
    }

    @Test
    void testIsValidDateCompleteMonthZero() {
        assertEquals(-14, Check.isValidDateComplete("2023/00/15-12:00:00")); // Mes 00 no es válido
    }

    @Test
    void testIsValidDateCompleteDayTooHigh() {
        assertEquals(-14, Check.isValidDateComplete("2023/01/32-12:00:00")); // Día 32 no existe
    }

    @Test
    void testIsValidDateCompleteMonthTooHigh() {
        assertEquals(-14, Check.isValidDateComplete("2023/13/15-12:00:00")); // Mes 13 no existe
    }

    @Test
    void testIsValidDateCompleteInvalidTimeFormat() {
        assertEquals(-14, Check.isValidDateComplete("2023/08/15-25:00:00")); // Hora inválida (mayor a 23)
    }

    @Test
    void testIsValidDateCompleteInvalidMinutes() {
        assertEquals(-14, Check.isValidDateComplete("2023/08/15-12:60:00")); // Minutos inválidos (mayor a 59)
    }

    @Test
    void testIsValidDateCompleteInvalidSeconds() {
        assertEquals(-14, Check.isValidDateComplete("2023/08/15-12:00:60")); // Segundos inválidos (mayor a 59)
    }

    /* TEST RANGE DOUBLE */

    @Test
    void testRangePositiveNumber() {
        assertEquals(0, Check.range(10.5)); // Número positivo
    }

    @Test
    void testRangeZero() {
        assertEquals(0, Check.range(0)); // Cero debería ser válido
    }

    @Test
    void testRangeNegativeNumber() {
        assertEquals(-6, Check.range(-5.3)); // Número negativo
    }

    /* TEST RANGE DISCOUNT */

    @Test
    void testRangeDiscountValidLowerBound() {
        assertEquals(0, Check.rangeDiscount(0.00)); // Límite inferior válido
    }

    @Test
    void testRangeDiscountValidUpperBound() {
        assertEquals(0, Check.rangeDiscount(50.00)); // Límite superior válido
    }

    @Test
    void testRangeDiscountWithinRange() {
        assertEquals(0, Check.rangeDiscount(25.5)); // Valor dentro del rango permitido
    }

    /* TEST RANGE INT */
    @Test
    void testRangeIntPositiveNumber() {
        assertEquals(0, Check.range(10)); // Número positivo
    }

    @Test
    void testRangeIntZero() {
        assertEquals(0, Check.range(0)); // Cero debería ser válido
    }

    @Test
    void testRangeIntNegativeNumber() {
        assertEquals(-6, Check.range(-5)); // Número negativo
    }

    /* TEST CHECK DNI */

    @Test
    void testCheckDNIValid() {
        assertEquals(0, Check.checkDNI("12345678A")); // DNI válido
    }

    @Test
    void testCheckDNILowerCaseLetter() {
        assertEquals(-9, Check.checkDNI("12345678a")); // Letra en minúscula
    }

    @Test
    void testCheckDNINoLetter() {
        assertEquals(-9, Check.checkDNI("12345678")); // No hay letra al final
    }

    @Test
    void testCheckDNILongString() {
        assertEquals(-9, Check.checkDNI("123456789A")); // Más de 8 dígitos
    }

    @Test
    void testCheckDNIMissingNumber() {
        assertEquals(-9, Check.checkDNI("12345A")); // Menos de 8 dígitos
    }

    @Test
    void testCheckDNIMixedCharacters() {
        assertEquals(-9, Check.checkDNI("12AB34CD5E")); // Caracteres no numéricos intercalados
    }

    @Test
    void testCheckDNINull() {
        assertEquals(-1, Check.checkDNI(null)); // Nulo
    }

    @Test
    void testCheckDNIEmpty() {
        assertEquals(-1, Check.checkDNI("")); // Vacio
    }

    @Test
    void testCheckDNIWhitSpace() {
        assertEquals(-1, Check.checkDNI("   ")); // Espcacios
    }

    /* TEST VALIDATE */
    @Test
    void testValidatePositivePrice() {
        assertEquals(0, Check.validate(10.99)); // Precio positivo
    }

    @Test
    void testValidateZeroPrice() {
        assertEquals(0, Check.validate(0.00)); // Cero debe ser válido
    }

    @Test
    void testValidateNegativePrice() {
        assertEquals(-6, Check.validate(-5.50)); // Precio negativo
    }

    /* TEST CHECK ISBN */

    @Test
    void testCheckISBNValid() {
        assertEquals(0, Check.checkISBN("978-3-16-148410-0")); // ISBN válido con guiones
    }

    @Test
    void testCheckISBNValidWithoutHyphens() {
        assertEquals(0, Check.checkISBN("9783161484100")); // ISBN válido sin guiones
    }

    @Test
    void testCheckISBNNull() {
        assertEquals(-1, Check.checkISBN(null)); // ISBN nulo
    }

    @Test
    void testCheckISBNTooShort() {
        assertEquals(-3, Check.checkISBN("978-3-16-148")); // ISBN con menos de 10 caracteres
    }

    @Test
    void testCheckISBNTooLong() {
        assertEquals(-8, Check.checkISBN("978-3-16-148410-0123")); // ISBN con más de 13 caracteres
    }

    @Test
    void testCheckISBNWithLetters() {
        assertEquals(-22, Check.checkISBN("978-3-16-ABCDEF-0")); // ISBN con caracteres no numéricos
    }

    @Test
    void testCheckISBNEmpty() {
        assertEquals(-1, Check.checkISBN("")); // ISBN vacío
    }

    @Test
    void testCheckISBNWithSpaces() {
        assertEquals(-1, Check.checkISBN("  ")); // ISBN con espacios
    }

    /* TEST CHECK MOBILE PHONE */

    @Test
    void testCheckMobilePhoneValid() {
        assertEquals(0, Check.checkMobilePhone("623456789")); // Número válido de 9 cifras
    }

    @Test
    void testCheckMobilePhoneNotStartsWith6Or7() {
        assertEquals(-17, Check.checkMobilePhone("123456789")); // Número válido de 9 cifras
    }

    @Test
    void testCheckMobilePhoneTooLong() {
        assertEquals(-17, Check.checkMobilePhone("1234567890")); // Número con más de 9 cifras
    }

    @Test
    void testCheckMobilePhoneNull() {
        assertEquals(0, Check.checkMobilePhone(null)); // Caso nulo
    }

    @Test
    void testCheckMobilePhoneEmpty() {
        assertEquals(-2, Check.checkMobilePhone("")); // String vacío
    }

    @Test
    void testCheckMobilePhoneWithLetters() {
        assertEquals(-17, Check.checkMobilePhone("1234abcd9")); // Contiene letras
    }

    @Test
    void testCheckMobilePhoneShort() {
        assertEquals(-17, Check.checkMobilePhone("12345")); // Menos de 9 cifras
    }

    @Test
    void testCheckMobilePhoneWithSpace() {
        assertEquals(-2, Check.checkMobilePhone("         "));
    }

    /* TEST GET ERROR MESSAGE */

    @Test
    void testGetErrorMessageNoError() {
        assertEquals("", Check.getErrorMessage(0));
    }

    @Test
    void testGetErrorMessageNullValue() {
        assertEquals("No puede ser null", Check.getErrorMessage(-1));
    }

    @Test
    void testGetErrorMessageEmptyValue() {
        assertEquals("No puede estar vacio", Check.getErrorMessage(-2));
    }

    @Test
    void testGetErrorMessageFewCharacters() {
        assertEquals("Has introducido pocos caracteres", Check.getErrorMessage(-3));
    }

    @Test
    void testGetErrorMessageIncorrectDateFormat() {
        assertEquals("Formato de fecha incorrecto", Check.getErrorMessage(-4));
    }

    @Test
    void testGetErrorMessageTooShortField() {
        assertEquals("El campo introducido es demasiado corto", Check.getErrorMessage(-5));
    }

    @Test
    void testGetErrorMessageNegativeNumber() {
        assertEquals("Has introducido un numero negativo", Check.getErrorMessage(-6));
    }

    @Test
    void testGetErrorMessageTooManyCharacters() {
        assertEquals("Has introducido demasiados caracteres", Check.getErrorMessage(-7));
    }

    @Test
    void testGetErrorMessageInvalidISBN() {
        assertEquals("Isbn no valido, introduce una cifra de 13 dígitos válido", Check.getErrorMessage(-8));
    }

    @Test
    void testGetErrorMessageInvalidDNI() {
        assertEquals("DNI no válido", Check.getErrorMessage(-9));
    }

    @Test
    void testGetErrorMessageInvalidEmailFormat() {
        assertEquals("Formato de email incorrecto", Check.getErrorMessage(-10));
    }

    @Test
    void testGetErrorMessageInvalidSoftwareFormat() {
        assertEquals("Formato de Software incorrecto", Check.getErrorMessage(-11));
    }

    @Test
    void testGetErrorMessageLatePayment() {
        assertEquals("El pago está atrasado", Check.getErrorMessage(-12));
    }

    @Test
    void testGetErrorMessageCorrectPayment() {
        assertEquals("El pago está correctamente", Check.getErrorMessage(-13));
    }

    @Test
    void testGetErrorMessageCorrectFormatButInvalidDate() {
        assertEquals("Formato correcto pero no válida", Check.getErrorMessage(-14));
    }

    @Test
    void testGetErrorMessageInvalidReferenceLow() {
        assertEquals("La referencia introducida no es válida, debe ser mayor a 1000", Check.getErrorMessage(-15));
    }

    @Test
    void testGetErrorMessageInvalidReferenceHigh() {
        assertEquals("La referencia introducida no es válida, debe ser menor a 10000", Check.getErrorMessage(-16));
    }

    @Test
    void testGetErrorMessageInvalidPhoneNumber() {
        assertEquals("el numero de teléfono debe ser un numero valido de 9 cifras", Check.getErrorMessage(-17));
    }

    @Test
    void testGetErrorMessageInvalidPackageFragility() {
        assertEquals("Por favor, introduce correctamente la fragilidad del paquete", Check.getErrorMessage(-18));
    }

    @Test
    void testGetErrorMessageUnsetOrderId() {
        assertEquals("el Id del Orden no ha sido seteado", Check.getErrorMessage(-19));
    }

    @Test
    void testGetErrorMessageDiscountTooHigh() {
        assertEquals("El descuento no puede ser mayor al 50% ", Check.getErrorMessage(-20));
    }

    @Test
    void testGetErrorMessageNoDetail() {
        assertEquals("No hay detalle", Check.getErrorMessage(-21));
    }

    @Test
    void testGetErrorMessageUnknownErrorCode() {
        assertEquals("No reconocible", Check.getErrorMessage(-99));
    }
}