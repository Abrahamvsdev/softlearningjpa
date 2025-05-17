package com.core.entities.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions.Dimensions;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public class DimensionsTest {

    // Valores válidos por defecto
    private static final double validWeight = 5.0;
    private static final double validHeight = 10.0;
    private static final double validWidth = 10.0;
    private static final boolean validFragile = true;
    private static final double validLength = 20.0;

    private Dimensions validDimensions;

    @BeforeEach
    public void setUp() throws BuildException {
        validDimensions = Dimensions.getInstanceDimensions(
                validWeight, validHeight, validWidth, validFragile, validLength);
    }

    // --------------------- Tests getInstance ---------------------
    @Test
    public void testDimensionsGetInstanceOk() {
        try {
            Dimensions d = Dimensions.getInstanceDimensions(
                    validWeight, validHeight, validWidth, validFragile, validLength);
            assertNotNull(d);
        } catch (Exception e) {
            fail("No deberia fallar, todos los campos son correctos");
        }
    }

    @Test
    public void testDimensionsGetInstanceInvalidWeight() {
        try {
            Dimensions.getInstanceDimensions(
                    -1.0, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por peso negativo");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nBad weight: El número es más pequeño de lo esperado\n",
                    e.getMessage());
        }
    }

    @Test
    public void testDimensionsGetInstanceInvalidHeight() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, -1.0, validWidth, validFragile, validLength);
            fail("Debería fallar por altura negativa");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nBad height: El número es más pequeño de lo esperado\n",
                    e.getMessage());
        }
    }

    @Test
    public void testDimensionsGetInstanceInvalideWidth() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, validHeight, -1.0, validFragile, validLength);
            fail("Debería fallar por ancho negativo");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nBad width: El número es más pequeño de lo esperado\n",
                    e.getMessage());
        }
    }

    @Test
    public void testDimensionsGetInstanceInvalidLength() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, validHeight, validWidth, validFragile, -1.0);
            fail("Debería fallar por longitud negativa");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nBad length: El número es más pequeño de lo esperado\n",
                    e.getMessage());
        }
    }

    @Test
    public void testDimensionsGetInstanceInvalidAll() {
        try {
            Dimensions.getInstanceDimensions(
                    -1.0, -2.0, -3.0, validFragile, -4.0);
            fail("Debería fallar por múltiples errores");
        } catch (BuildException e) {
            String expectedMessage = "Not possible to create the dimensions: \n"
                    + "Bad weight: El número es más pequeño de lo esperado\n"
                    + "Bad height: El número es más pequeño de lo esperado\n"
                    + "Bad width: El número es más pequeño de lo esperado\n"
                    + "Bad length: El número es más pequeño de lo esperado\n";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    // --------------------- Tests de getters ---------------------
    @Test
    public void testGetWeight() {
        assertEquals(validWeight, validDimensions.getWeight(), 0.001);
    }

    @Test
    public void testGetHeight() {
        assertEquals(validHeight, validDimensions.getHeight(), 0.001);
    }

    @Test
    public void testGetWidth() {
        assertEquals(validWidth, validDimensions.getWidth(), 0.001);
    }

    @Test
    public void testGetFragile() {
        assertEquals(validFragile, validDimensions.getFragile());
    }

    @Test
    public void testGetLength() {
        assertEquals(validLength, validDimensions.getLength(), 0.001);
    }

    @Test
    public void testGetVolume() {
        double expectedVolume = validWidth * validHeight * validLength;
        assertEquals(expectedVolume, validDimensions.getVolume(), 0.001);
    }

    // --------------------- Tests de representación ---------------------
    @Test
    public void testGetDimensionstoString() {
        String expected = "Weight: 5.0 kg\n"
                + "Height: 10.0 cm\n"
                + "Width: 10.0 cm\n"
                + "Fragile: true\n"
                + "Length: 20.0 cm\n"
                + "Volume: 2000.0 cubic cm";
        assertEquals(expected, validDimensions.getDimensionstoString());
    }

    // --------------------- Tests de setters ---------------------

    /* SET WEIGHT */

    @Test
    public void testSetWeight() {
        assertEquals(0, validDimensions.setWeight(5.0));
    }

    @Test
    public void testSetWeightZero() {
        assertEquals(-23, validDimensions.setWeight(0));
    }

    @Test
    public void testSetWeightNegative() {
        assertEquals(-23, validDimensions.setWeight(-5));
    }

    /* SET HEIGHT */

    @Test
    public void testSetHeight() {
        assertEquals(0, validDimensions.setHeight(7.5));
    }

    @Test
    public void testSetHeightZero() {
        assertEquals(-23, validDimensions.setHeight(0));
    }

    @Test
    public void testSetHeightNegative() {
        assertEquals(-23, validDimensions.setHeight(-5.5));
    }

    /* SET WIDTH */

    @Test
    public void testSetWidth() {
        assertEquals(0, validDimensions.setWidth(7.5));
    }

    @Test
    public void testSetWidthZero() {
        assertEquals(-23, validDimensions.setWidth(0));
    }

    @Test
    public void testSetWidthNegative() {
        assertEquals(-23, validDimensions.setWidth(-5.5));
    }

    /* SET FRAGILE */

    @Test
    public void testSetFragileTrue() {
        assertEquals(0, validDimensions.setFragile(true));
    }

    @Test
    public void testSetFragileFalse() {
        assertEquals(0, validDimensions.setFragile(false));
    }

    /* SET LENGTH */

    @Test
    public void testSetLength() {
        assertEquals(0, validDimensions.setLength(7.5));
    }

    @Test
    public void testSetLengthZero() {
        assertEquals(-23, validDimensions.setLength(0));
    }

    @Test
    public void testSetLengthNegative() {
        assertEquals(-23, validDimensions.setLength(-5.5));
    }
}
