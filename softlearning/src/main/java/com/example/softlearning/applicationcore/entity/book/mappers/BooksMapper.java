package com.example.softlearning.applicationcore.entity.book.mappers;


import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.model.Books;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;




public class BooksMapper {
    public static Books booksFromDTO(BooksDTO bdto) throws BuildException{
        return Books.getInstance(bdto.getIdent(),
                                bdto.getPrice(),
                                bdto.getDelayPay(),
                                bdto.getDiscount(),
                                bdto.getType(),
                                bdto.getPayMethod(),
                                bdto.getDate(),
                                bdto.getAuthor(),
                                bdto.getIsbn(),
                                bdto.getCover(),
                                bdto.getPage(),
                                bdto.getGenre(),
                                bdto.getEditorial(),
                                bdto.getWeight(),
                                bdto.getHeight(),
                                bdto.getWidth(),
                                bdto.getFragile(),
                                bdto.getLength());
    }

    public static BooksDTO dtoFromBooks(Books b){

        return new BooksDTO(
        
                                b.getIdent(),
                                b.getPrice(),
                                b.getDelayPay(),
                                b.getDiscount(),
                                b.getType(),
                                b.getPayMethod(),
                                b.getDate(),
                                b.getAuthor(),
                                b.getIsbn(),
                                b.getCover(),
                                b.getPage(),
                                b.getGenre(),
                                b.getEditorial(),
                                b.getWeight(),
                                b.getHeight(),
                                b.getWidth(),
                                b.getFragile(),
                                b.getLength(),
                                b.getVolume()
                                );
    }

}
