package com.example.softlearning.applicationcore.entity.book.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BooksDTO {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ident")
    private String ident;
    @Column(name = "title") //el type será el title, esto le dice al JPA que el campo title de la tabla books se corresponde con el atributo type de la clase BooksDTO, pero las funciones dentro del codigo deberán llamarse como las defino en el propio
    private String type;
    @Column(name = "paymethod") 
    private String payMethod; 
    @Column(name = "date") 
    private String date; 
    @Column(name = "author") 
    private String author; 
    @Column(name = "isbn") 
    private String isbn;
    @Column(name = "cover")  
    private String cover;
    @Column(name = "genre")  
    private String genre; 
    @Column(name = "editorial") 
    private String editorial;
    @Column(name = "price") 
    private double price;
    @Column(name = "discount") 
    private double discount;
    @Column(name = "weight") 
    private double weight;
    @Column(name = "height") 
    private double height;
    @Column(name = "width") 
    private double width;
    @Column(name = "length") 
    private double length;
    @Column(name = "volume") 
    private double volume;
    @Column(name = "delaypay") 
    private boolean delayPay;
    @Column(name = "fragile")
    private boolean fragile;
    @Column(name = "page")
    private int page;

    protected BooksDTO() {
    }

    public BooksDTO(String ident,double price ,boolean delayPay,double discount, String type, String payMethod, String date,
                String author, String isbn, String cover, int page, String genre, String editorial, 
                double weight, double height, double width, boolean fragile, double length, double volume) {

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
    public String getIdent() {
        return ident;
    }
    public String getType() {
        return type;
    }
    public String getPayMethod() {
        return payMethod;
    }
    public String getDate() {
        return date;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getCover() {
        return cover;
    }
    public String getGenre() {
        return genre;
    }
    public String getEditorial() {
        return editorial;
    }
    public double getPrice() {
        return price;
    }
    public double getDiscount() {
        return discount;
    }
    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
    public boolean getDelayPay() {
        return delayPay;
    }
    public int getPage() {
        return page;
    }

    public boolean getFragile() {
        return fragile;
    }
    public double getVolume() {
        return volume;
    }
    

}
