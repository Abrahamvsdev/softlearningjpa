package com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CheckTest {

    // Test IsNull
    @Test
    void testIsNullOk() {
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

    // Test CheckEmail
    @Test
    void testCheckEmailOk() {
        assertEquals(0, Check.checkEmail("checkEmail@gmail.com"));
    }

    @Test
    void testCheckEmailWithNumbers() {
        assertEquals(0, Check.checkEmail("user123@example.com"));
    }

    @Test
    void testCheckEmailWithDots() {
        assertEquals(0, Check.checkEmail("user.name@example.com"));
    }

    @Test
    void testCheckEmailWithHyphen() {
        assertEquals(0, Check.checkEmail("user-name@example.com"));
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

    // minLength

    @Test
    void testMinLengthValid() {
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

    // Test checkBoolean
    @Test
    void testCheckBooleanTrue() {
        assertEquals(-12, Check.checkBoolean(true));
    }

    @Test
    void testCheckBooleanFalse() {
        assertEquals(0, Check.checkBoolean(false));
    }

    // Test MaxLength

    @Test
    void testMaxLengthValid() {
        assertEquals(0, Check.maxLength("123456789012345"));
    }

    @Test
    void testMaxLengthMoreThanFifteen() {
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

    // Test MinMaxLength

    @Test
    void testMinMaxLengthValid() {
        assertEquals(0, Check.minMaxLength("validLength"));
    }

    @Test
    void testMinMaxLengthExactlyThree() {
        assertEquals(0, Check.minMaxLength("abc"));
    }

    @Test
    void testMinMaxLengthExactlyFifteen() {
        assertEquals(0, Check.minMaxLength("123456789012345"));
    }

    @Test
    void testMinMaxLengthLessThanThree() {
        assertEquals(-3, Check.minMaxLength("ab"));
    }

    @Test
    void testMinMaxLengthMoreThanFifteen() {
        assertEquals(-7, Check.minMaxLength("123-4567890123-456"));
    }

    @Test
    void testMinMaxLengthEmptyString() {
        assertEquals(-1, Check.minMaxLength(""));
    }

    @Test
    void testMinMaxLengthSpaceString() {
        assertEquals(-1, Check.maxLength("           "));
    }

    @Test
    void testMinMaxLengthNullString() {
        assertEquals(-1, Check.minMaxLength(null));
    }

    // Test IsValidDate

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
        assertEquals(-4, Check.isValidDate("2023-08-15")); // Formato incorrecto (yyyy-MM-dd en lugar de dd-MM-yyyy)
    }

    @Test
    void testIsValidDateWithLetters() {
        assertEquals(-14, Check.isValidDate("aa-bb-cccc")); // Formato inválido
    }

    @Test
    void testIsValidDateWithExtraCharacters() {
        assertEquals(-4, Check.isValidDate("15/08/2023")); // Usa "/" en lugar de "-"
    }

    @Test
    void testIsValidDateNull() {
        assertEquals(0, Check.isValidDate(null)); // Manejo de fecha nula
    }

    @Test
    void testIsValidDateEmptyString() {
        assertEquals(-4, Check.isValidDate("")); // Cadena vacía no es válida
    }

    @Test
    void testIsValidDateOutOfRangeYear() {
        assertEquals(-4, Check.isValidDate("15-08-1800")); // Año fuera del rango definido (19xx o 20xx)
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
        assertEquals(-4, Check.isValidDate("00-12-2023")); // Día 00 no es válido
    }

    @Test
    void testIsValidDateMonthZero() {
        assertEquals(-4, Check.isValidDate("15-00-2023")); // Mes 00 no es válido
    }

    @Test
    void testIsValidDateDayTooHigh() {
        assertEquals(-4, Check.isValidDate("32-01-2023")); // Día 32 no existe
    }

    @Test
    void testIsValidDateMonthTooHigh() {
        assertEquals(-4, Check.isValidDate("15-13-2023")); // Mes 13 no existe
    }

    @Test
    void testIsValidDateWithWhitespace() {
        assertEquals(-4, Check.isValidDate(" 15-08-2023 ")); // Espacios antes y después deben invalidar
    }

    @Test
    void testIsValidDateWithSpecialCharacters() {
        assertEquals(-4, Check.isValidDate("15@08@2023")); // Formato incorrecto con caracteres especiales
    }

    @Test
    void testIsValidDateWithOnlyYear() {
        assertEquals(-4, Check.isValidDate("2023")); // Solo el año no es válido
    }

    @Test
    void testIsValidDateWithOnlyMonthAndYear() {
        assertEquals(-4, Check.isValidDate("08-2023")); // Falta el día
    }

    @Test
    void testIsValidDateWithOnlyDayAndMonth() {
        assertEquals(-4, Check.isValidDate("15-08")); // Falta el año
    }

    @Test
    void testIsValidDateFutureDate() {
        assertEquals(0, Check.isValidDate("01-01-2099")); // Fecha válida en el futuro
    }

    @Test
    void testIsValidDatePastDate() {
        assertEquals(0, Check.isValidDate("01-01-1900")); // Fecha válida en el pasado dentro del rango aceptado
    }

    @Test
    void testIsValidDateExtremeFuture() {
        assertEquals(-4, Check.isValidDate("01-01-3000")); // Año fuera del rango permitido (19xx-20xx)
    }

    @Test
    void testIsValidDateExtremePast() {
        assertEquals(-4, Check.isValidDate("01-01-1899")); // Año fuera del rango permitido
    }

    // Test IsValidDateComplete
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
        assertEquals(-4, Check.isValidDateComplete("15-08-2023 12:30:45")); // Formato incorrecto (dd-MM-yyyy HH:mm:ss en vez de yyyy/MM/dd-HH:mm:ss)
    }

    @Test
    void testIsValidDateCompleteWithLetters() {
        assertEquals(-4, Check.isValidDateComplete("aaaa/bb/cc-12:30:45")); // Formato inválido
    }

    @Test
    void testIsValidDateCompleteWithExtraCharacters() {
        assertEquals(-4, Check.isValidDateComplete("2023-08-15T12:30:45")); // Usa "T" en lugar de "-"
    }

    @Test
    void testIsValidDateCompleteNull() {
        assertEquals(0, Check.isValidDateComplete(null)); // Manejo de fecha nula
    }

    @Test
    void testIsValidDateCompleteEmptyString() {
        assertEquals(-4, Check.isValidDateComplete("")); // Cadena vacía no es válida
    }

    @Test
    void testIsValidDateCompleteOutOfRangeYear() {
        assertEquals(-14, Check.isValidDateComplete("1800/08/15-12:30:45")); // Año fuera del rango definido
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

    @Test
    void testIsValidDateCompleteWithWhitespace() {
        assertEquals(-4, Check.isValidDateComplete(" 2023/08/15-12:30:-45 ")); // Espacios antes y después deben invalidar
    }

    @Test
    void testIsValidDateCompleteFutureDate() {
        assertEquals(0, Check.isValidDateComplete("2099/12/31-23:59:59")); // Fecha válida en el futuro
    }

    @Test
    void testIsValidDateCompletePastDate() {
        assertEquals(0, Check.isValidDateComplete("1900/01/01-00:00:00")); // Fecha válida en el pasado dentro del rango aceptado
    }

    @Test
    void testIsValidDateCompleteExtremeFuture() {
        assertEquals(-4, Check.isValidDateComplete("3000/01/01-00:00:00")); // Año fuera del rango permitido
    }

    @Test
    void testIsValidDateCompleteExtremePast() {
        assertEquals(-4, Check.isValidDateComplete("1899/12/31-23:59:59")); // Año fuera del rango permitido
    }

    // Test range
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

    @Test
    void testRangeLargePositiveNumber() {
        assertEquals(0, Check.range(999999999.99)); // Número muy grande positivo
    }

    @Test
    void testRangeLargeNegativeNumber() {
        assertEquals(-6, Check.range(-999999999.99)); // Número muy grande negativo
    }

    @Test
    void testRangeSmallPositiveNumber() {
        assertEquals(0, Check.range(0.00001)); // Número positivo muy pequeño
    }

    @Test
    void testRangeSmallNegativeNumber() {
        assertEquals(-6, Check.range(-0.00001)); // Número negativo muy pequeño
    }

    // Test RangeDiscount

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

    @Test
    void testRangeDiscountNegativeValue() {
        assertEquals(-6, Check.rangeDiscount(-5.0)); // Descuento negativo
    }

    @Test
    void testRangeDiscountGreaterThanLimit() {
        assertEquals(-20, Check.rangeDiscount(51.0)); // Descuento mayor a 50
    }

    // Test Range
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

    // Test checkDNI - Revisar
    @Test
    void testCheckDNIValid() {
        assertEquals(0, Check.checkDNI("123-45678A")); // DNI válido
    }

    @Test
    void testCheckDNILowerCaseLetter() {
        assertEquals(-9, Check.checkDNI("123-45678a")); // Letra en minúscula
    }

    @Test
    void testCheckDNINoLetter() {
        assertEquals(-9, Check.checkDNI("123-45678")); // No hay letra al final
    }

    @Test
    void testCheckDNINoDigits() {
        assertEquals(-9, Check.checkDNI("ABCDEFGA")); // No hay números, solo letras
    }

    @Test
    void testCheckDNILongString() {
        assertEquals(-9, Check.checkDNI("123-456789A")); // Más de 8 dígitos
    }

    @Test
    void testCheckDNIMissingNumber() {
        assertEquals(-9, Check.checkDNI("123-45A")); // Menos de 8 dígitos
    }

    @Test
    void testCheckDNIMixedCharacters() {
        assertEquals(-9, Check.checkDNI("12AB3-4CD5E")); // Caracteres no numéricos intercalados
    }

    // Test validate 
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

    // Test CheckISBN - Revisar

    
    @Test
    void testCheckISBNValid() {
        assertEquals(0, Check.checkISBN("978-3-16-1-48-410-0")); // ISBN válido con guiones
    }

    @Test
    void testCheckISBNValidWithoutHyphens() {
        assertEquals(0, Check.checkISBN("9783161-48-4100")); // ISBN válido sin guiones
    }

    @Test
    void testCheckISBNNull() {
        assertEquals(-1, Check.checkISBN(null)); // ISBN nulo
    }

    @Test
    void testCheckISBNTooShort() {
        assertEquals(-8, Check.checkISBN("978-3-16-1-48-4")); // ISBN con menos de 13 caracteres
    }

    @Test
    void testCheckISBNTooLong() {
        assertEquals(-8, Check.checkISBN("978-3-16-1-48-410-0123")); // ISBN con más de 13 caracteres
    }

    @Test
    void testCheckISBNWithLetters() {
        assertEquals(-8, Check.checkISBN("978-3-16-ABCDEF-0")); // ISBN con caracteres no numéricos
    }

    @Test
    void testCheckISBNEmpty() {
        assertEquals(-1, Check.checkISBN("")); // ISBN vacío
    }

    // Test CheckMobilePhone

    @Test
    void testCheckMobilePhoneValid() {
        assertEquals(0, Check.checkMobilePhone("123-456789")); // Número válido de 9 cifras
    }

    @Test
    void testCheckMobilePhoneTooLong() {
        assertEquals(-17, Check.checkMobilePhone("123-4567890")); // Número con más de 9 cifras
    }

    @Test
    void testCheckMobilePhoneNull() {
        assertEquals(0, Check.checkMobilePhone(null)); // Caso nulo (la función no maneja explícitamente esto)
    }

    @Test
    void testCheckMobilePhoneEmpty() {
        assertEquals(-1, Check.checkMobilePhone("")); // String vacío, se debería manejar en Check.isNull()
    }

    @Test
    void testCheckMobilePhoneWithLetters() {
        assertEquals(-17, Check.checkMobilePhone("123-4abcd9")); // Contiene letras (la función actual no lo detecta)
    }

    @Test
    void testCheckMobilePhoneShort() {
        assertEquals(0, Check.checkMobilePhone("123-45")); // Menos de 9 cifras, pero la función no lo maneja explícitamente
    }

    @Test
    void testCheckMobilePhoneWithSpace() {
        assertEquals(-1, Check.checkMobilePhone("         "));
    }

    // Test GetErrorMessage

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
        assertEquals("Formato correcto pero no válida", Check.getErrorMessage(-1-4));
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

