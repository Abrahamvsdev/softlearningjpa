package com.example.softlearning.applicationcore.entity.book.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions.Dimensions;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions.Storable;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.products.Product;

public class Books extends Product implements Storable {

    protected LocalDate date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    protected String author;
    protected String isbn;
    protected String cover;
    protected int page;
    protected String genre;
    protected String editorial;
    protected Dimensions dim;
    protected boolean fragile;

    protected Books() {

    }

    public static Books getInstance(
            String ident,
            double price,
            boolean delayPay,
            double discount,
            String type,
            String payMethod,
            String date,
            String author,
            String isbn,
            String cover,
            int page,
            String genre,
            String editorial,
            double weight,
            double height,
            double width,
            boolean fragile,
            double length) throws BuildException {

        StringBuilder errors = new StringBuilder();
        int errorCode;
        Books libro1 = new Books();
        try {
            libro1.dim = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        } catch (BuildException e) {
            throw new BuildException("Error en las dimensiones: " + e.getMessage());
        }

        try {
            libro1.product(ident, price, delayPay, discount, type, payMethod);

        } catch (BuildException ex) {
            throw new BuildException("Error en product: " + ex.getMessage());
        }

        if ((errorCode = libro1.setDate(date)) != 0) {
            errors.append("Bad date: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setAuthor(author)) != 0) {
            errors.append("Bad author: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setIsbn(isbn)) != 0) {
            errors.append("Bad isbn: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setCover(cover)) != 0) {
            errors.append("Bad cover: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setPage(page)) != 0) {
            errors.append("Bad page: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setGenre(genre)) != 0) {
            errors.append("Bad genre: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = libro1.setEditorial(editorial)) != 0) {
            errors.append("Bad editorial: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (errors.length() > 0) {
            throw new BuildException("No es posible crear el libro: \n" + errors.toString());

        }
        return libro1;
    }

    // getter
    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getCover() {
        return this.cover;
    }

    public int getPage() {
        return this.page;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getDate() {
        if (this.date == null) {
            return "";
        }
        return this.date.format(formatter);
    }

    public String getEditorial() {
        return this.editorial;
    }

    public Dimensions getDim() {
        return this.dim;
    }

    public double getWeight() {
        return dim.getWeight();
    }

    public double getHeight() {
        return dim.getHeight();
    }

    public double getWidth() {
        return dim.getWidth();
    }

    public boolean getFragile() {
        return this.fragile;
    }

    public double getLength() {
        return dim.getLength();
    }

    public double getVolume() {
        return dim.getLength() * dim.getWidth() * dim.getHeight();
    }

    // setter
    public int setAuthor(String author) {
        int errorAuthor = Check.checkLength(author, 3, 20); // Usamos checkLength para validar
        if (errorAuthor == 0) {
            this.author = author;
        }
        return errorAuthor;
    }

    public int setDate(String date) {
        int errorDate = Check.isValidDate(date);  // Usamos isValidDate para validar

        if (errorDate == 0) {
            // Si es valida, la analizamos y asignamos
            this.date = LocalDate.parse(date, this.formatter);
        }

        return errorDate;
    }

    public int setIsbn(String isbn) {
        int errorIsbn = Check.checkISBN(isbn);
        if (errorIsbn == 0) {
            this.isbn = isbn;
        }
        return errorIsbn;
    }

    public int setCover(String cover) {
        int errorCover = Check.checkLength(cover, 3, 10); // Usamos checkLength para validar
        if (errorCover == 0) {
            this.cover = cover;
        }
        return errorCover;
    }

    public int setPage(int page) {
        int errorPage = Check.range(page, 1, 10000); // Usamos range con valores razonables
        if (errorPage == 0) {
            this.page = page;
        }
        return errorPage;
    }

    public int setGenre(String genre) {
        int errorGenre = Check.checkLength(genre, 3, 50); // Usamos checkLength para validar
        if (errorGenre == 0) {
            this.genre = genre;
        }
        return errorGenre;
    }

    public int setEditorial(String editorial) {
        int errorEditorial = Check.checkLength(editorial, 3, 50); // Usamos checkLength para validar
        if (errorEditorial == 0) {
            this.editorial = editorial;
        }
        return errorEditorial;
    }

    public void setDim(Dimensions dim) {
        this.dim = dim;
    }

    @Override
    public double volume() {
        return dim != null ? dim.getVolume() : 0;
    }

    @Override
    public String getDetails() {
        return "ID: " + this.getIdent() + "\n"
                + "Precio: $" + this.getPrice() + "\n"
                + "Autor: " + this.getAuthor() + "\n"
                + "ISBN: " + this.getIsbn() + "\n"
                + "Tapa: " + this.getCover() + "\n"
                + "Paginas: " + this.getPage() + "\n"
                + "Género: " + this.getGenre() + "\n"
                + "Editorial: " + this.getEditorial() + "\n"
                + "Fecha: " + this.getDate() + "\n"
                + "Dimensiones (AxAxL): " + this.getDim().getWidth() + "x" + this.getDim().getHeight() + "x" + this.getDim().getLength() + " cm\n"
                + "Peso: " + this.getDim().getWeight() + " kg\n";
    }

    @Override
    public boolean isFlexible() {
        return false; 
    }

    @Override
    public boolean isHeavy() {
        return dim != null && dim.getWeight() > 10.0;
    }

}
