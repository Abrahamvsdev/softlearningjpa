package com.core.entities.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.order.model.OrderDetails;


public class OrderDetailsTest {
     private final int validAmount = 2;
    private final String validDetailRef = "REF123";
    private final double validPrice = 100.0;
    private final int validDiscount = 10;

    private OrderDetails validDetail;

    @BeforeEach
    public void setUp() throws Exception {
        validDetail = OrderDetails.getInstance(validAmount, validDetailRef, validPrice, validDiscount);
    }

    // ==================== VALIDACIÓN EN CREACIÓN ====================

    @Test
    public void testOrderDetailsCreateOk() {
        try {
            OrderDetails od = OrderDetails.getInstance(validAmount, validDetailRef, validPrice, validDiscount);
            assertNotNull(od);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testOrderDetailsCreateInvalidAmount() {
        try {
            OrderDetails.getInstance(0, validDetailRef, validPrice, validDiscount);
            fail("Debería fallar por amount inválido");
        } catch (Exception e) {
            assertEquals("Bad amount: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    @Test
    public void testOrderDetailsCreateInvalidDetailRef() {
        try {
            OrderDetails.getInstance(validAmount, "", validPrice, validDiscount);
            fail("Debería fallar por detailRef inválido");
        } catch (Exception e) {
            assertEquals("Bad detailRef: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderDetailsCreateInvalidPrice() {
        try {
            OrderDetails.getInstance(validAmount, validDetailRef, -10.0, validDiscount);
            fail("Debería fallar por price inválido");
        } catch (Exception e) {
            assertEquals("Bad price: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    @Test
    public void testOrderDetailsCreateInvalidDiscount() {
        try {
            OrderDetails.getInstance(validAmount, validDetailRef, validPrice, 200);
            fail("Debería fallar por discount inválido");
        } catch (Exception e) {
            assertEquals("Bad discount: El descuento no puede ser mayor al 50%\n", e.getMessage());
        }
    }

    @Test
    public void testOrderDetailsCreateMultipleErrors() {
        try {
            OrderDetails.getInstance(0, "", -1.0, 200);
            fail("Debería fallar con múltiples errores");
        } catch (Exception e) {
            String expected =
                    "Bad amount: El número es más pequeño de lo esperado\n" +
                    "Bad detailRef: No puede estar vacío\n" +
                    "Bad price: El número es más pequeño de lo esperado\n" +
                    "Bad discount: El descuento no puede ser mayor al 50%\n";
            assertEquals(expected, e.getMessage());
        }
    }

    // ==================== TESTS DE GETTERS ====================

    @Test
    public void testGetAmount() {
        assertEquals(validAmount, validDetail.getAmount());
    }

    @Test
    public void testGetDetailRef() {
        assertEquals(validDetailRef, validDetail.getDetailRef());
    }

    @Test
    public void testGetPrice() {
        assertEquals(validPrice, validDetail.getPrice());
    }

    @Test
    public void testGetDiscount() {
        assertEquals(validDiscount, validDetail.getDiscount());
    }

    @Test
    public void testCalculateSubtotal() {
        double expectedSubtotal = validPrice * (1 - validDiscount / 100.0) * validAmount;
        assertEquals(expectedSubtotal, validDetail.calculateSubtotal());
    }

    // ==================== TESTS DE SETTERS ====================

    /* SET AMOUNT */

    @Test
    public void testSetAmountValid() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(0, helper.testSetAmount(5));
    }

    @Test
    public void testSetAmountTooLow() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-23, helper.testSetAmount(0));
    }

    @Test
    public void testSetAmountTooHigh() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-24, helper.testSetAmount(1001));
    }

    /* SET DETAIL REF */

    @Test
    public void testSetDetailRefValid() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(0, helper.testSetDetailRef("NEWREF"));
    }

    @Test
    public void testSetDetailRefNull() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-1, helper.testSetDetailRef(null));
    }

    @Test
    public void testSetDetailRefEmpty() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-2, helper.testSetDetailRef(""));
    }

    @Test
    public void testSetDetailRefWithSpace() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-2, helper.testSetDetailRef("   "));
    }

    @Test
    public void testSetDetailRefTooLong() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-7, helper.testSetDetailRef("TOO_LONG_REF"));
    }

    /* SET PRICE */

    @Test
    public void testSetPriceValid() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(0, helper.testSetPrice(500.0));
    }

    @Test
    public void testSetPriceTooLow() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-23, helper.testSetPrice(-1.0));
    }

    @Test
    public void testSetPriceTooHigh() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-24, helper.testSetPrice(100001.0));
    }

    /* SET DISCOUNT */

    @Test
    public void testSetDiscountValid() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(0, helper.testSetDiscount(25));
    }

    @Test
    public void testSetDiscountTooLow() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-23, helper.testSetDiscount(-5));
    }

    @Test
    public void testSetDiscountTooHigh() {
        OrderDetailsHelper helper = new OrderDetailsHelper();
        assertEquals(-20, helper.testSetDiscount(51));
    }


    // ==================== TEST toString ====================

    @Test
    public void testGetDetailstoStringContent() {
        String expected = "amount:" + validAmount +
                ", detailRef:" + validDetailRef +
                ", price:" + validPrice +
                ", discount:" + validDiscount +
                ", subtotal:" + validDetail.calculateSubtotal();

        assertEquals(expected, validDetail.getDetailstoString());
    }

}
