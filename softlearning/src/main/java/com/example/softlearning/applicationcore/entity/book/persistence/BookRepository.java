package com.example.softlearning.applicationcore.entity.book.persistence;

import java.util.List;
import java.util.Optional;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;



public interface BookRepository {

    public Optional<BooksDTO> findByIdent(String ident);

    public List<BooksDTO> findByType(String type);

    public List<BooksDTO> findByPartialType(String ttype);

    public Integer countByPartialType(String type);

    public BooksDTO save(BooksDTO book);

    public void deleteById(String id);

    //public List<BooksDTO> findAll();

    
	}


