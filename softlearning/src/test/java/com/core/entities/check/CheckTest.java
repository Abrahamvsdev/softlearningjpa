package com.core.entities.check;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;

public class CheckTest {

    /* TEST IS NULL */
    @Test
    void testIsNull() {
        assertEquals(0, Check.isNull("Hola"));
    }

    @Test
    void testIsNullEmpty() {
        assertEquals(0, Check.isNull(""));
    }

    @Test
    void testIsNullSpace() {
        assertEquals(0, Check.isNull("  "));
    }

    @Test
    void testIsNullNull() {
        assertEquals(-1, Check.isNull(null));
    }

    /* TEST IS EMPTY */
    @Test
    void testIsEmptyNotEmpty() {
        assertEquals(0, Check.isEmpty("solomillo"));
    }

    @Test
    void testIsEmpty() {
        assertEquals(-2, Check.isEmpty(""));
    }

    @Test
    void testIsEmptySpace() {
        assertEquals(-2, Check.isEmpty("  "));
    }

    @Test
    void testIsEmptyNull() {
        assertEquals(-1, Check.isEmpty(null));
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

    /* TEST CHECK LENGTH */
    @Test
    void testCheckLengthValid() {
        assertEquals(0, Check.checkLength("abc", 3, 10));
    }

    @Test
    void testCheckLengthLessThanMin() {
        assertEquals(-3, Check.checkLength("ab", 3, 10));
    }

    @Test
    void testCheckLengthMoreThanMax() {
        assertEquals(-7, Check.checkLength("abcdefghijklmno", 3, 10));
    }

    @Test
    void testCheckLengthEmptyString() {
        assertEquals(-2, Check.checkLength("", 3, 10));
    }

    @Test
    void testCheckLengthSpaceString() {
        assertEquals(-2, Check.checkLength("   ", 3, 10));
    }

    @Test
    void testCheckLengthNullString() {
        assertEquals(-1, Check.checkLength(null, 3, 10));
    }

    /* TEST IS TRUE */
    @Test
    void testIsTrueTrue() {
        assertEquals(0, Check.isTrue(true));
    }

    @Test
    void testIsTrueFalse() {
        assertEquals(-12, Check.isTrue(false));
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
        assertEquals(-2, Check.isValidDate("")); // Cadena vacía no es válida
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
        assertEquals(-2, Check.isValidDateComplete("")); // Cadena vacía no es válida
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
        assertEquals(0, Check.range(10.5, 0, 100));
    }

    @Test
    void testRangeZero() {
        assertEquals(0, Check.range(0, 0, 100));
    }

    @Test
    void testRangeNegativeNumber() {
        assertEquals(-23, Check.range(-5.3, 0, 100));
    }

    /* TEST RANGE DISCOUNT */
    @Test
    void testRangeDiscountValidLowerBound() {
        assertEquals(0, Check.rangeDiscount(0.00));
    }

    @Test
    void testRangeDiscountValidUpperBound() {
        assertEquals(0, Check.rangeDiscount(50.00));
    }

    @Test
    void testRangeDiscountWithinRange() {
        assertEquals(0, Check.rangeDiscount(25.5));
    }

    @Test
    void testRangeDiscountAboveRange() {
        assertEquals(-20, Check.rangeDiscount(51));
    }

    @Test
    void testRangeDiscountBelowRange() {
        assertEquals(-23, Check.rangeDiscount(-1));
    }

    /* TEST RANGE INT */
    @Test
    void testRangeIntPositiveNumber() {
        assertEquals(0, Check.range(10, 0, 100));
    }

    @Test
    void testRangeIntZero() {
        assertEquals(0, Check.range(0, 0, 100));
    }

    @Test
    void testRangeIntNegativeNumber() {
        assertEquals(-23, Check.range(-5, 0, 100));
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
        assertEquals(-2, Check.checkDNI("")); // Vacio
    }

    @Test
    void testCheckDNIWhitSpace() {
        assertEquals(-2, Check.checkDNI("   ")); // Espcacios
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
        assertEquals(-7, Check.checkISBN("978-3-16-148410-0123")); // ISBN con más de 13 caracteres
    }

    @Test
    void testCheckISBNWithLetters() {
        assertEquals(-22, Check.checkISBN("978-3-16-ABCDEF-0")); // ISBN con caracteres no numéricos
    }

    @Test
    void testCheckISBNEmpty() {
        assertEquals(-2, Check.checkISBN("")); // ISBN vacío
    }

    @Test
    void testCheckISBNWithSpaces() {
        assertEquals(-2, Check.checkISBN("  ")); // ISBN con espacios
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
        assertEquals(-1, Check.checkMobilePhone(null)); // Caso nulo
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
        assertEquals("No puede estar vacío", Check.getErrorMessage(-2));
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
        assertEquals("Has introducido un número negativo", Check.getErrorMessage(-6));
    }

    @Test
    void testGetErrorMessageTooManyCharacters() {
        assertEquals("Has introducido demasiados caracteres", Check.getErrorMessage(-7));
    }

    @Test
    void testGetErrorMessageFinishBeforeInit() {
        assertEquals("La fecha de fin no puede ser menor a la fecha de inicio", Check.getErrorMessage(-8));
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
        assertEquals("Formato de software incorrecto", Check.getErrorMessage(-11));
    }

    @Test
    void testGetErrorMessageLatePayment() {
        assertEquals("El valor es falso", Check.getErrorMessage(-12));
    }

    @Test
    void testGetErrorMessageCorrectPayment() {
        assertEquals("El valor es verdadero", Check.getErrorMessage(-13));
    }

    @Test
    void testGetErrorMessageCorrectFormatButInvalidDate() {
        assertEquals("Formato de fecha incorrecto", Check.getErrorMessage(-14));
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
        assertEquals("El número de teléfono debe ser un número válido de 9 cifras", Check.getErrorMessage(-17));
    }

    @Test
    void testGetErrorMessageInvalidPackageFragility() {
        assertEquals("Por favor, introduce correctamente la fragilidad del paquete", Check.getErrorMessage(-18));
    }

    @Test
    void testGetErrorMessageUnsetOrderId() {
        assertEquals("El Id del orden no ha sido seteado", Check.getErrorMessage(-19));
    }

    @Test
    void testGetErrorMessageDiscountTooHigh() {
        assertEquals("El descuento no puede ser mayor al 50%", Check.getErrorMessage(-20));
    }

    @Test
    void testGetErrorMessageNoDetail() {
        assertEquals("No hay detalle", Check.getErrorMessage(-21));
    }

    @Test
    void testGetErrorMessageInvalidISBN() {
        assertEquals("El ISBN no está formado correctamente", Check.getErrorMessage(-22));
    }

    @Test
    void testGetErrorMessageNumberTooSmall() {
        assertEquals("El número es más pequeño de lo esperado", Check.getErrorMessage(-23));
    }

    @Test
    void testGetErrorMessageNumberTooLarge() {
        assertEquals("El número es más grande de lo esperado", Check.getErrorMessage(-24));
    }

    @Test
    void testGetErrorMessageBadDate() {
        assertEquals("No puedes introducir fecha de fin sin fecha de inicio", Check.getErrorMessage(-25));
    }

    @Test
    void testGetErrorMessageUnknownErrorCode() {
        assertEquals("No reconocible", Check.getErrorMessage(-99));
    }
}
