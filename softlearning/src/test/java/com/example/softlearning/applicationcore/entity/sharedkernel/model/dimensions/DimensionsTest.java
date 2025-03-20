package com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
}