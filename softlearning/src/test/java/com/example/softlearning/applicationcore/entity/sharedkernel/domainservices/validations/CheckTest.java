package com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CheckTest {

    // ---------------------- isNull ----------------------
    @Test
    public void testIsNull_NullString() {
        assertEquals(-1, Check.isNull(null));
    }

    @Test
    public void testIsNull_EmptyString() {
        assertEquals(-1, Check.isNull(""));
    }

    @Test
    public void testIsNull_WhitespaceString() {
        assertEquals(-1, Check.isNull("   "));
    }

    @Test
    public void testIsNull_ValidString() {
        assertEquals(0, Check.isNull("Valid"));
    }

    // ---------------------- checkEmail ----------------------
    @Test
    public void testCheckEmail_Valid() {
        assertEquals(0, Check.checkEmail("user@example.com"));
    }

    @Test
    public void testCheckEmail_MissingAt() {
        assertEquals(-10, Check.checkEmail("userexample.com"));
    }

    @Test
    public void testCheckEmail_InvalidDomain() {
        assertEquals(-10, Check.checkEmail("user@.com"));
    }

    @Test
    public void testCheckEmail_Null() {
        assertEquals(-1, Check.checkEmail(null));
    }

    // ---------------------- minLength ----------------------
    @Test
    public void testMinLength_TooShort() {
        assertEquals(-3, Check.minLength("ab"));
    }

    @Test
    public void testMinLength_Exact() {
        assertEquals(0, Check.minLength("abc"));
    }

    @Test
    public void testMinLength_Null() {
        assertEquals(-1, Check.minLength(null));
    }

    // ---------------------- checkBoolean ----------------------
    @Test
    public void testCheckBoolean_True() {
        assertEquals(-12, Check.checkBoolean(true));
    }

    @Test
    public void testCheckBoolean_False() {
        assertEquals(0, Check.checkBoolean(false));
    }

    // ---------------------- maxLength ----------------------
    @Test
    public void testMaxLength_TooLong() {
        assertEquals(-7, Check.maxLength("1234567890123456"));
    }

    @Test
    public void testMaxLength_Exact() {
        assertEquals(0, Check.maxLength("123456789012345"));
    }

    // ---------------------- minMaxLength ----------------------
    @Test
    public void testMinMaxLength_TooShort() {
        assertEquals(-3, Check.minMaxLength("ab"));
    }

    @Test
    public void testMinMaxLength_TooLong() {
        assertEquals(-7, Check.minMaxLength("1234567890123456"));
    }

    @Test
    public void testMinMaxLength_Valid() {
        assertEquals(0, Check.minMaxLength("12345"));
    }

    // ---------------------- isValidDate ----------------------
    @Test
    public void testIsValidDate_Valid() {
        assertEquals(0, Check.isValidDate("02-03-2025"));
    }

    @Test
    public void testIsValidDate_InvalidFormat() {
        assertEquals(-14, Check.isValidDate("2025-12-31"));
    }

    @Test
    public void testIsValidDate_InvalidDate() {
        assertEquals(-14, Check.isValidDate("30-02-2025"));
    }

    // ---------------------- isValidDateComplete ----------------------
    @Test
    public void testIsValidDateComplete_Valid() {
        assertEquals(0, Check.isValidDateComplete("2025/12/31-23:59:59"));
    }

    @Test
    public void testIsValidDateComplete_InvalidFormat() {
        assertEquals(-14, Check.isValidDateComplete("02-03-1990"));
    }

    // ---------------------- range (double) ----------------------
    @Test
    public void testRangeDouble_Negative() {
        assertEquals(-6, Check.range(-5.0));
    }

    @Test
    public void testRangeDouble_Valid() {
        assertEquals(0, Check.range(10.5));
    }

    // ---------------------- rangeDiscount ----------------------
    @Test
    public void testRangeDiscount_OverLimit() {
        assertEquals(-20, Check.rangeDiscount(51.0));
    }

    @Test
    public void testRangeDiscount_Valid() {
        assertEquals(0, Check.rangeDiscount(50.0));
    }

    // ---------------------- range (int) ----------------------
    @Test
    public void testRangeInt_Negative() {
        assertEquals(-6, Check.range(-5));
    }

    // ---------------------- checkDNI ----------------------
    @Test
    public void testCheckDNI_Valid() {
        assertEquals(0, Check.checkDNI("12345678Z"));
    }

    @Test
    public void testCheckDNI_InvalidFormat() {
        assertEquals(-9, Check.checkDNI("1234Z"));
    }

    // ---------------------- validate (double) ----------------------
    @Test
    public void testValidateDouble_Negative() {
        assertEquals(-6, Check.validate(-10.0));
    }

    // ---------------------- checkISBN ----------------------
    @Test
    public void testCheckISBN10_Valid() {
        assertEquals(0, Check.checkISBN("0-306-40615-2"));
    }

    @Test
    public void testCheckISBN13_Valid() {
        assertEquals(0, Check.checkISBN("978-3-16-148410-0"));
    }

    @Test
    public void testCheckISBN_Invalid() {
        assertEquals(-3, Check.checkISBN("12345"));
    }

    // ---------------------- checkMobilePhone ----------------------
    @Test
    public void testCheckMobilePhone_Valid() {
        assertEquals(0, Check.checkMobilePhone("612345678"));
    }

    @Test
    public void testCheckMobilePhone_InvalidStart() {
        assertEquals(-17, Check.checkMobilePhone("512345678"));
    }

    // ---------------------- getErrorMessage ----------------------
    @Test
    public void testGetErrorMessage_KnownCode() {
        assertEquals("Has introducido un numero negativo", Check.getErrorMessage(-6));
    }

    @Test
    public void testGetErrorMessage_UnknownCode() {
        assertEquals("No reconocible", Check.getErrorMessage(-999));
    }
}