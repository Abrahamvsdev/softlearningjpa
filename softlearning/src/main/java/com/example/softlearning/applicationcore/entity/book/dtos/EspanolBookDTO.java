package com.example.softlearning.applicationcore.entity.book.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "Libros")

public class EspanolBookDTO {
    private String ident, type, payMethod, date, author, isbn, cover, genre, editorial;
    private double price, discount, weight, height, width, length,volume;
    private boolean delayPay,fragile;
    private int page;
    public EspanolBookDTO(){};

    public EspanolBookDTO(String ident,double price ,boolean delayPay,double discount, String type, String payMethod, String date,
                String author, String isbn, String cover, int page, 
                String genre, String editorial, double weight, double height, double width, boolean fragile, double length, double volume) {

                    this.ident = ident;
                    this.price = price;
                    this.delayPay = delayPay;
                    this.discount = discount;
                    this.type = type;
                    this.payMethod = payMethod;
                    this.date = date;
                    this.author = author;
                    this.isbn = isbn;
                    this.cover = cover;
                    this.page = page;
                    this.genre = genre;
                    this.editorial = editorial;
                    this.weight = weight;
                    this.height = height;
                    this.width = width;
                    this.fragile = fragile;
                    this.length = length;
                    this.volume = volume;
                }


    @JsonGetter("identidad")
    public String getIdent() {
        return ident;
    }
    @JsonGetter("tipo")
    public String getType() {
        return type;
    }
    @JsonGetter("metodoPago")
    public String getPayMethod() {
        return payMethod;
    }
    @JsonGetter("fecha")
    public String getDate() {
        return date;
    }
    @JsonGetter("autor")
    public String getAuthor() {
        return author;
    }
    @JsonGetter("isbn")
    public String getIsbn() {
        return isbn;
    }
    @JsonGetter("portada")
    public String getCover() {
        return cover;
    }
    @JsonGetter("genero")
    public String getGenre() {
        return genre;
    }
    @JsonGetter("editorial")
    public String getEditorial() {
        return editorial;
    }
    @JsonGetter("precio")
    public double getPrice() {
        return price;
    }
    @JsonGetter("descuento")
    public double getDiscount() {
        return discount;
    }
    @JsonGetter("peso")
    public double getWeight() {
        return weight;
    }  
    @JsonGetter("altura")
    public double getHeight() {
        return height;
    }
    @JsonGetter("ancho")
    public double getWidth() {
        return width;
    }
    @JsonGetter("largo")
    public double getLength() {
        return length;
    }
    @JsonGetter("pagoDiferido")
    public boolean getDelayPay() {
        return delayPay;
    }
    @JsonGetter("paginas")
    public int getPage() {
        return page;
    }
    @JsonGetter("fragil")
    public boolean getFragile() {
        return fragile;
    }
    @JsonGetter("volumen")
    public double getVolume() {
        return volume;
    }

    //setters

    @JsonSetter("identidad")
    public void setIdent(String ident) {
        this.ident = ident;
    }
    @JsonSetter("tipo")
    public void setType(String type) {
        this.type = type;
    }
    @JsonSetter("metodoPago")
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    @JsonSetter("fecha")
    public void setDate(String date) {
        this.date = date;
    }
    @JsonSetter("autor")
    public void setAuthor(String author) {
        this.author = author;
    }
    @JsonSetter("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @JsonSetter("portada")
    public void setCover(String cover) {
        this.cover = cover;
    }
    @JsonSetter("genero")
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @JsonSetter("editorial")
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @JsonSetter("precio")
    public void setPrice(double price) {
        this.price = price;
    }
    @JsonSetter("descuento")
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    @JsonSetter("peso")
    public void setWeight(double weight) {
        this.weight = weight;
    }
    @JsonSetter("altura")
    public void setHeight(double height) {
        this.height = height;
    }
    @JsonSetter("ancho")
    public void setWidth(double width) {
        this.width = width;
    }
    @JsonSetter("largo")
    public void setLength(double length) {
        this.length = length;
    }
    @JsonSetter("pagoDiferido")
    public void setDelayPay(boolean delayPay) {
        this.delayPay = delayPay;
    }
    @JsonSetter("paginas")
    public void setPage(int page) {
        this.page = page;
    }
    @JsonSetter("fragil")
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }
    @JsonSetter("volumen")
    public void setVolume(double volume) {
        this.volume = volume;
    }




















}
