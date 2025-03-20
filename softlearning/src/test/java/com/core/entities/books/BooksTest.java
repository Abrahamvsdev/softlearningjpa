package com.core.entities.books;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.mappers.BooksMapper;
import com.example.softlearning.applicationcore.entity.book.model.Books;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class BooksTest {
    public static void main(String[] args) throws Exception, BuildException, ServiceException {
        //ejemplo de serializacion
        
        Books b;
        b = Books.getInstance(
            "1234",
            10.0,
            false,
            0.0,
            "titulo tal",
            "true",
            "15-08-2023",
            "willingli",
            "1234567891234",
            "cover1",
            10,
            "genre",
            "editorial",
            10.0,
            10.0,
            10.0,
            false,
            10.0
             // TODO esto es el volumen, que lo estoy dejando así hardcodeado para pruebas
        );
        System.out.println(b.getDetails());

        //Mapper
        BooksDTO bdto = BooksMapper.dtoFromBooks(b);
        System.out.println("Autor: " + bdto.getAuthor());

        Books bcopy = BooksMapper.booksFromDTO(bdto);
        System.out.println("Mapper Book: " + bcopy.getDetails());
    



    }
}
