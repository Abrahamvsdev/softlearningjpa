package com.core.entities.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.book.model.Books;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions.Dimensions;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public class BooksTest {
    private final String validIdent = "BOOK123";
    private final double validPrice = 50.0;
    private final boolean validDelayPay = false;
    private final double validDiscount = 5.0;
    private final String validType = "Novela";
    private final String validPayMethod = "Efectivo";
    private final String validDate = "12-05-2024";
    private final String validAuthor = "Autor Prueba";
    private final String validIsbn = "9781234567890";
    private final String validCover = "TapaBlanda";
    private final int validPage = 300;
    private final String validGenre = "Ficción";
    private final String validEditorial = "EditorialX";
    private final double validWeight = 1.2;
    private final double validHeight = 1.0;
    private final double validWidth = 3.0;
    private final boolean validFragile = false;
    private final double validLength = 3.0;

    // ==================== TESTS DE CREACIÓN CON ERRORES ====================
    @Test
    public void testBooksCreateInvalidIdent() {
        try {
            Books.getInstance("", validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por ident vacío");
        } catch (BuildException e) {
            assertEquals("Error en product: Not possible to create the product: \nBad ident: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testBooksCreateInvalidPrice() {
        try {
            Books.getInstance(validIdent, -10.0, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por precio negativo");
        } catch (BuildException e) {
            assertEquals("Error en product: Not possible to create the product: \nBad price: El número es más pequeño de lo esperado\n", e.getMessage());
        }
    }

    @Test
    public void testBooksCreateInvalidType() {
        try {
            Books.getInstance(validIdent, validPrice, validDelayPay, validDiscount, "", validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por tipo vacío");
        } catch (BuildException e) {
            assertEquals("Error en product: Not possible to create the product: \nBad type: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testBooksCreateInvalidIsbn() {
        try {
            Books.getInstance(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, "123", validCover, validPage, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por ISBN corto");
        } catch (BuildException e) {
            // El mensaje puede variar según la implementación de errores
            assertEquals(true, e.getMessage().contains("Bad isbn"));
        }
    }

    @Test
    public void testBooksCreateInvalidPage() {
        try {
            Books.getInstance(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, -1, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por número de páginas negativo");
        } catch (BuildException e) {
            assertEquals(true, e.getMessage().contains("Bad page"));
        }
    }

    @Test
    public void testBooksCreateInvalidGenre() {
        try {
            Books.getInstance(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, "", validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por género vacío");
        } catch (BuildException e) {
            assertEquals(true, e.getMessage().contains("Bad genre"));
        }
    }

    @Test
    public void testBooksCreateInvalidEditorial() {
        try {
            Books.getInstance(validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, validGenre, "",
                validWeight, validHeight, validWidth, validFragile, validLength);
            fail("Debería fallar por editorial vacía");
        } catch (BuildException e) {
            assertEquals(true, e.getMessage().contains("Bad editorial"));
        }
    }

    private Books book;

    @BeforeEach
    public void setUp() throws BuildException {
        book = Books.getInstance(
            validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
            validAuthor, validIsbn, validCover, validPage, validGenre, validEditorial,
            validWeight, validHeight, validWidth, validFragile, validLength
        );
    }

    // ==================== TESTS DE SETTERS ====================
    @Test
    public void testSetAuthor() {
        assertEquals(0, book.setAuthor(validAuthor));
    }

    @Test
    public void testSetAuthorShort() {
        assertEquals(-3, book.setAuthor("a"));
    }

    @Test
    public void testSetAuthorLong() {
        assertEquals(-7, book.setAuthor("autordemasiadolargolargosoparalibroautorunmontondelargoparalibro"));
    }

    @Test
    public void testSetAuthorNull() {
        assertEquals(-1, book.setAuthor(null));
    }

    @Test
    public void testSetAuthorEmpty() {
        assertEquals(-2, book.setAuthor(""));
    }

    @Test
    public void testSetDate() {
        assertEquals(0, book.setDate(validDate));
    }

    @Test
    public void testSetDateInvalid() {
        assertEquals(-14, book.setDate("2024/05/12"));
    }

    @Test
    public void testSetIsbn() {
        assertEquals(0, book.setIsbn(validIsbn));
    }

    @Test
    public void testSetIsbnShort() {
        assertEquals(-3, book.setIsbn("123"));
    }

    @Test
    public void testSetIsbnLong() {
        assertEquals(-7, book.setIsbn("97812345678901234567890"));
    }

    @Test
    public void testSetIsbnNull() {
        assertEquals(-1, book.setIsbn(null));
    }

    @Test
    public void testSetIsbnEmpty() {
        assertEquals(-2, book.setIsbn(""));
    }

    @Test
    public void testSetCover() {
        assertEquals(0, book.setCover(validCover));
    }

    @Test
    public void testSetCoverShort() {
        assertEquals(-3, book.setCover("a"));
    }

    @Test
    public void testSetCoverLong() {
        assertEquals(-7, book.setCover("cubiertademasiadolarga"));
    }

    @Test
    public void testSetCoverNull() {
        assertEquals(-1, book.setCover(null));
    }

    @Test
    public void testSetCoverEmpty() {
        assertEquals(-2, book.setCover(""));
    }

    @Test
    public void testSetPage() {
        assertEquals(0, book.setPage(validPage));
    }

    @Test
    public void testSetPageNegative() {
        assertEquals(-23, book.setPage(-10));
    }

    @Test
    public void testSetGenre() {
        assertEquals(0, book.setGenre(validGenre));
    }

    @Test
    public void testSetGenreShort() {
        assertEquals(-3, book.setGenre("a"));
    }

    @Test
    public void testSetGenreLong() {
        assertEquals(-7, book.setGenre("generodemasiadolargoparalibrogenerodemasiadolargoparalibro"));
    }

    @Test
    public void testSetGenreNull() {
        assertEquals(-1, book.setGenre(null));
    }

    @Test
    public void testSetGenreEmpty() {
        assertEquals(-2, book.setGenre(""));
    }

    @Test
    public void testSetEditorial() {
        assertEquals(0, book.setEditorial(validEditorial));
    }

    @Test
    public void testSetEditorialShort() {
        assertEquals(-3, book.setEditorial("a"));
    }

    @Test
    public void testSetEditorialLong() {
        assertEquals(-7, book.setEditorial("editorialdemasiadolargaparaunlibroeditorialdemasiadolarga"));
    }

    @Test
    public void testSetEditorialNull() {
        assertEquals(-1, book.setEditorial(null));
    }

    @Test
    public void testSetEditorialEmpty() {
        assertEquals(-2, book.setEditorial(""));
    }

    // ==================== TESTS DE GETTERS ====================
    @Test
    public void testGetAuthor() {
        assertEquals(validAuthor, book.getAuthor());
    }

    @Test
    public void testGetIsbn() {
        assertEquals(validIsbn, book.getIsbn());
    }

    @Test
    public void testGetCover() {
        assertEquals(validCover, book.getCover());
    }

    @Test
    public void testGetPage() {
        assertEquals(validPage, book.getPage());
    }

    @Test
    public void testGetGenre() {
        assertEquals(validGenre, book.getGenre());
    }

    @Test
    public void testGetDate() {
        assertEquals(validDate, book.getDate());
    }

    @Test
    public void testGetEditorial() {
        assertEquals(validEditorial, book.getEditorial());
    }

    @Test
    public void testBooksCreateOk() {
        try {
            Books b = Books.getInstance(
                validIdent, validPrice, validDelayPay, validDiscount, validType, validPayMethod, validDate,
                validAuthor, validIsbn, validCover, validPage, validGenre, validEditorial,
                validWeight, validHeight, validWidth, validFragile, validLength
            );
            assertNotNull(b);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testBooksDimensions() {
        Dimensions dim = book.getDim();
        assertNotNull(dim);
        assertEquals(validWeight, dim.getWeight(), 0.0001);
        assertEquals(validHeight, dim.getHeight(), 0.0001);
        assertEquals(validWidth, dim.getWidth(), 0.0001);
        assertEquals(validFragile, dim.getFragile());
        assertEquals(validLength, dim.getLength(), 0.0001);
        // Test volumen calculado
        assertEquals(validLength * validWidth * validHeight, book.getVolume(), 0.0001);
    }

    @Test
    public void testSetDim() throws BuildException {
        Dimensions newDim = Dimensions.getInstanceDimensions(2.0, 2.0, 3.0, true, 4.0);
        book.setDim(newDim);
        assertEquals(2.0, book.getDim().getWeight(), 0.0001);
        assertEquals(2.0, book.getDim().getHeight(), 0.0001);
        assertEquals(3.0, book.getDim().getWidth(), 0.0001);
        assertEquals(true, book.getDim().getFragile());
        assertEquals(4.0, book.getDim().getLength(), 0.0001);
    }
}
