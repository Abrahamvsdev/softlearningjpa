package com.core.entities.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.products.Product;

// Clase concreta para testear la clase abstracta Product
class ProductImpl extends Product {
    @Override
    public String getDetails() {
        return "Detalles de producto de prueba";
    }
}

public class ProductTest {
    private final String validIdent = "PROD123";
    private final double validPrice = 100.0;
    private final boolean validDelayPay = true;
    private final double validDiscount = 10.0;
    private final String validType = "Libro";
    private final String validPayMethod = "Tarjeta";

    private ProductImpl product;

    @BeforeEach
    public void setUp() throws BuildException {
        product = new ProductImpl();
        product.product(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod);
    }

    @Test
    public void testProductCreateOk() {
        try {
            ProductImpl p = new ProductImpl();
            p.product(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod);
            assertNotNull(p);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testProductCreateInvalidIdent() {
        try {
            ProductImpl p = new ProductImpl();
            p.product("", validPrice, validDelayPay, validDiscount, validType, validPayMethod);
            fail("Debería fallar por ident vacío");
        } catch (BuildException e) {
            assertEquals("Not possible to create the product: \nBad ident: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testProductCreateInvalidPrice() {
        try {
            ProductImpl p = new ProductImpl();
            p.product(validIdent, -10.0, validDelayPay, validDiscount, validType, validPayMethod);
            fail("Debería fallar por precio negativo");
        } catch (BuildException e) {
            assertEquals("Not possible to create the product: \nBad price: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    @Test
    public void testProductCreateInvalidType() {
        try {
            ProductImpl p = new ProductImpl();
            p.product(validIdent, validPrice, validDelayPay, validDiscount, "", validPayMethod);
            fail("Debería fallar por tipo vacío");
        } catch (BuildException e) {
            assertEquals("Not possible to create the product: \nBad type: No puede estar vacío\n", e.getMessage());
        }
    }

    // ==================== TESTS DE SETTERS ====================
    @Test
    public void testSetIdent() {
        assertEquals(0, product.setIdent(validIdent));
    }

    @Test
    public void testSetIdentShort() {
        assertEquals(-3, product.setIdent("a"));
    }

    @Test
    public void testSetIdentLong() {
        assertEquals(-7, product.setIdent("identdemasiadolargoparaproducto"));
    }

    @Test
    public void testSetIdentNull() {
        assertEquals(-1, product.setIdent(null));
    }

    @Test
    public void testSetIdentEmpty() {
        assertEquals(-2, product.setIdent(""));
    }

    @Test
    public void testSetPrice() {
        assertEquals(0, product.setPrice(validPrice));
    }

    @Test
    public void testSetPriceNegative() {
        assertEquals(-23, product.setPrice(-10.0));
    }

    @Test
    public void testSetDelayPay() {
        assertEquals(0, product.setDelayPay(true));
    }

    @Test
    public void testSetDiscount() {
        assertEquals(0, product.setDiscount(validDiscount));
    }

    @Test
    public void testSetDiscountTooHigh() {
        assertEquals(-20, product.setDiscount(60.0));
    }

    @Test
    public void testSetType() {
        assertEquals(0, product.setType(validType));
    }

    @Test
    public void testSetTypeShort() {
        assertEquals(-3, product.setType("a"));
    }

    @Test
    public void testSetTypeLong() {
        assertEquals(-7, product.setType("tipodemasiadolargoparaproducto"));
    }

    @Test
    public void testSetTypeNull() {
        assertEquals(-1, product.setType(null));
    }

    @Test
    public void testSetTypeEmpty() {
        assertEquals(-2, product.setType(""));
    }

    @Test
    public void testSetPayMethod() {
        assertEquals(0, product.setPayMethod(validPayMethod));
    }

    @Test
    public void testSetPayMethodShort() {
        assertEquals(-3, product.setPayMethod("a"));
    }

    @Test
    public void testSetPayMethodLong() {
        assertEquals(-7, product.setPayMethod("paymethoddemasiadolargoparaproducto"));
    }

    @Test
    public void testSetPayMethodNull() {
        assertEquals(-1, product.setPayMethod(null));
    }

    @Test
    public void testSetPayMethodEmpty() {
        assertEquals(-2, product.setPayMethod(""));
    }

    // ==================== TESTS DE GETTERS ====================
    @Test
    public void testGetIdent() {
        assertEquals(validIdent, product.getIdent());
    }

    @Test
    public void testGetPrice() {
        assertEquals(validPrice, product.getPrice());
    }

    @Test
    public void testGetDelayPay() {
        assertEquals(validDelayPay, product.getDelayPay());
    }

    @Test
    public void testGetDiscount() {
        assertEquals(validDiscount, product.getDiscount());
    }

    @Test
    public void testGetType() {
        assertEquals(validType, product.getType());
    }

    @Test
    public void testGetPayMethod() {
        assertEquals(validPayMethod, product.getPayMethod());
    }
}
