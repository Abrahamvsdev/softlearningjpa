package com.example.softlearning.applicationcore.entity.book.persistence;

import java.util.List;
import java.util.Optional;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;



public interface BookRepository {

    public Optional<BooksDTO> findById(String id);

    public List<BooksDTO> findByIdent(String title);

    
    public List<BooksDTO> findByPartialIdent(String title);

    
    public Integer countByPartialTitle(String title);

    public BooksDTO save(BooksDTO book);

    public void deleteById(String id);

	
		
	}


