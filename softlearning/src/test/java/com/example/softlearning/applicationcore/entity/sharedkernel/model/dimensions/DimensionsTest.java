package com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

class DimensionsTest {

    @Test
    void testDefaultConstructorInitializesDefaults() {
        Dimensions dimensions = new Dimensions();
        assertEquals(0.0, dimensions.getWeight());
        assertEquals(0.0, dimensions.getHeight());
        assertEquals(0.0, dimensions.getWidth());
        assertEquals(0.0, dimensions.getLength());
        assertFalse(dimensions.getFragile());
        assertEquals(0.0, dimensions.getVolume());
    }

    @Test
    void testGetInstanceDimensionsValidValues() throws BuildException {
        double weight = 10.5;
        double height = 20.0;
        double width = 30.0;
        boolean fragile = true;
        double length = 40.0;

        Dimensions dimensions = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);

        assertEquals(weight, dimensions.getWeight());
        assertEquals(height, dimensions.getHeight());
        assertEquals(width, dimensions.getWidth());
        assertEquals(length, dimensions.getLength());
        assertEquals(fragile, dimensions.getFragile());
        // Check volume calculation (width * height * length)
        assertEquals(width * height * length, dimensions.getVolume());
    }

    @Test
    void testGetDimensionstoStringFormat() throws BuildException {
        double weight = 5.0;
        double height = 15.0;
        double width = 10.0;
        boolean fragile = false;
        double length = 20.0;

        Dimensions dimensions = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        String dimensionsString = dimensions.getDimensionstoString();

        assertTrue(dimensionsString.contains("Height: " + height));
        assertTrue(dimensionsString.contains("Weight: " + weight));
        assertTrue(dimensionsString.contains("Width: " + width));
        assertTrue(dimensionsString.contains("Fragile: " + fragile));
        assertTrue(dimensionsString.contains("Length: " + length));
        // Volume calculated as width * height * length
        assertTrue(dimensionsString.contains("Volume: " + (width * height * length)));
    }

    // New test: Verify that a BuildException is thrown when an invalid (negative) value is provided.
    @Test
    void testGetInstanceDimensionsThrowsBuildExceptionForInvalidValues() {
        double invalidValue = -1.0;
        double validValue = 10.0;
        boolean fragile = false;

        Exception exception = assertThrows(BuildException.class, () -> {
            Dimensions.getInstanceDimensions(invalidValue, validValue, validValue, fragile, validValue);
        });
        assertTrue(exception.getMessage().contains("Not possible to create the dimensions"));
    }

    // New test: Test individual setter methods update the properties correctly.
    @Test
    void testSettersIndividually() {
        Dimensions dimensions = new Dimensions();
        int errorWeight = dimensions.setWeight(12.3);
        int errorHeight = dimensions.setHeight(23.4);
        int errorWidth = dimensions.setWidth(34.5);
        int errorLength = dimensions.setLength(45.6);
        int errorFragile = dimensions.setFragile(true);

        assertEquals(0, errorWeight);
        assertEquals(0, errorHeight);
        assertEquals(0, errorWidth);
        assertEquals(0, errorLength);
        assertEquals(0, errorFragile);

        assertEquals(12.3, dimensions.getWeight());
        assertEquals(23.4, dimensions.getHeight());
        assertEquals(34.5, dimensions.getWidth());
        assertEquals(45.6, dimensions.getLength());
        assertTrue(dimensions.getFragile());
    }

    @Test
    void testVolumeCalculationAfterUpdate() {
        Dimensions dimensions = new Dimensions();
        dimensions.setWidth(2.0);
        dimensions.setHeight(3.0);
        dimensions.setLength(4.0);

        double expectedVolume = 2.0 * 3.0 * 4.0;
        assertEquals(expectedVolume, dimensions.getVolume());
    }

    // Test: Verifica que actualizar individualmente una dimensión actualiza correctamente el volumen
    @Test
    void testVolumeRecalculationAfterMultipleUpdates() {
        Dimensions dimensions = new Dimensions();
        // Inicialmente se asignan valores
        dimensions.setWidth(1.0);
        dimensions.setHeight(2.0);
        dimensions.setLength(3.0);
        double initialVolume = 1.0 * 2.0 * 3.0;
        assertEquals(initialVolume, dimensions.getVolume());

        // Actualización de las dimensiones
        dimensions.setWidth(4.0);
        dimensions.setHeight(5.0);
        dimensions.setLength(6.0);
        double updatedVolume = 4.0 * 5.0 * 6.0;
        assertEquals(updatedVolume, dimensions.getVolume());
    }

    // Test: Validar la creación de Dimensions con valores límite (por ejemplo, cero)
    @Test
    void testInstanceDimensionsWithZeroValues() throws BuildException {
        double weight = 0.0;
        double height = 0.0;
        double width = 0.0;
        double length = 0.0;
        boolean fragile = false;

        Dimensions dimensions = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);

        assertEquals(0.0, dimensions.getWeight());
        assertEquals(0.0, dimensions.getHeight());
        assertEquals(0.0, dimensions.getWidth());
        assertEquals(0.0, dimensions.getLength());
        assertFalse(dimensions.getFragile());
        assertEquals(0.0, dimensions.getVolume());
    }

    // Test: Verifica el método toString para un formato exacto esperado (si se conoce el formato)
    @Test
    void testDimensionstoStringExactFormat() throws BuildException {
        double weight = 7.5;
        double height = 10.0;
        double width = 5.0;
        boolean fragile = true;
        double length = 2.0;

        Dimensions dimensions = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        String expectedFormat = "Weight: " + weight + " kg\n"
                + "Height: " + height + " cm\n"
                + "Width: " + width + " cm\n"
                + "Fragile: " + fragile + "\n"
                + "Length: " + length + " cm\n"
                + "Volume: " + (width * height * length) + " cubic cm";

        // Suponiendo que getDimensionstoString retorna el string con ese formato
        assertEquals(expectedFormat, dimensions.getDimensionstoString());
    }

    // Test: Verifica que no se pueda actualizar a un valor negativo mediante setters
    @Test
    void testSettersRejectNegativeValues() {
        Dimensions dimensions = new Dimensions();

        // Asumimos que los setters devuelven un código de error distinto de 0 para valores negativos
        assertEquals(-6, dimensions.setWeight(-10.0));
        assertEquals(-6, dimensions.setHeight(-5.0));
        assertEquals(-6, dimensions.setWidth(-3.0));
        assertEquals(-6, dimensions.setLength(-7.0));
    }
}
