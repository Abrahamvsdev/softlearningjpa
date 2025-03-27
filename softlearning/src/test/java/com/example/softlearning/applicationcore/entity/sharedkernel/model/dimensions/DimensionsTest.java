package com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public class DimensionsTest {

    // Valores válidos por defecto
    private static final double validWeight = 5.0;
    private static final double validHeight = 10.0;
    private static final double validWidth = 15.0;
    private static final boolean validFragile = true;
    private static final double validLength = 20.0;

    private Dimensions validDimensions;

    @BeforeEach
    public void setUp() throws BuildException {
        validDimensions = Dimensions.getInstanceDimensions(
                validWeight, validHeight, validWidth, validFragile, validLength
        );
    }

    //--------------------- Tests para creación inválida ---------------------
    @Test
    public void testNegativeWeight() {
        try {
            Dimensions.getInstanceDimensions(
                    -1.0, validHeight, validWidth, validFragile, validLength
            );
            fail("Debería fallar por peso negativo");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nHas introducido un numero negativo\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testNegativeHeight() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, -1.0, validWidth, validFragile, validLength
            );
            fail("Debería fallar por altura negativa");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nHas introducido un numero negativo\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testNegativeWidth() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, validHeight, -1.0, validFragile, validLength
            );
            fail("Debería fallar por ancho negativo");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nHas introducido un numero negativo\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testNegativeLength() {
        try {
            Dimensions.getInstanceDimensions(
                    validWeight, validHeight, validWidth, validFragile, -1.0
            );
            fail("Debería fallar por longitud negativa");
        } catch (BuildException e) {
            assertEquals(
                    "Not possible to create the dimensions: \nHas introducido un numero negativo\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testMultipleInvalidDimensions() {
        try {
            Dimensions.getInstanceDimensions(
                    -1.0, -2.0, -3.0, validFragile, -4.0
            );
            fail("Debería fallar por múltiples errores");
        } catch (BuildException e) {
            String expectedMessage = "Not possible to create the dimensions: \n"
                    + "Has introducido un numero negativo\n"
                    + "Has introducido un numero negativo\n"
                    + "Has introducido un numero negativo\n"
                    + "Has introducido un numero negativo\n";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    //--------------------- Tests de getters ---------------------
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

    //--------------------- Tests de representación ---------------------
    @Test
    public void testGetDimensionstoString() {
        String expected = "Weight: 5.0 kg\n"
                + "Height: 10.0 cm\n"
                + "Width: 15.0 cm\n"
                + "Fragile: true\n"
                + "Length: 20.0 cm\n"
                + "Volume: 3000.0 cubic cm";
        assertEquals(expected, validDimensions.getDimensionstoString());
    }

    //--------------------- Tests de setters ---------------------
    @Test
    public void testSetWeight_Valid() {
        assertEquals(0, validDimensions.setWeight(7.5));
    }

    @Test
    public void testSetWeight_Invalid() {
        assertEquals(-6, validDimensions.setWeight(-2.0));
    }

    @Test
    public void testSetFragile() {
        assertEquals(0, validDimensions.setFragile(false));
    }

    @Test
    public void testSetHeight_Valid() {
        assertEquals(0, validDimensions.setHeight(15.0));
    }

    @Test
    public void testSetHeight_Invalid() {
        assertEquals(-6, validDimensions.setHeight(-1.0));
    }

    @Test
    public void testSetWidth_Valid() {
        assertEquals(0, validDimensions.setWidth(20.0));
    }

    @Test
    public void testSetWidth_Invalid() {
        assertEquals(-6, validDimensions.setWidth(-1.0));
    }

    @Test
    public void testZeroValues() throws BuildException {
        Dimensions dim = Dimensions.getInstanceDimensions(
                0.0, 0.0, 0.0, false, 0.0
        );
        assertEquals(0.0, dim.getVolume(), 0.001);
    }

    @Test
    public void testSetLength_Valid() {
        assertEquals(0, validDimensions.setLength(25.0));
    }

    @Test
    public void testSetLength_Invalid() {
        assertEquals(-6, validDimensions.setLength(-1.0));
    }

}
