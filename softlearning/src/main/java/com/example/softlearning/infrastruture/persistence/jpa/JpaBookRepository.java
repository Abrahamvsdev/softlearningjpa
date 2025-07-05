package com.example.softlearning.infrastruture.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.persistence.BookRepository;

import jakarta.transaction.Transactional;

@Repository
public interface JpaBookRepository extends JpaRepository<BooksDTO, String>, BookRepository {
    public Optional<BooksDTO> findByIdent(String ident);

    public List<BooksDTO> findByType(String title);

    @Query(value="SELECT b FROM BooksDTO b WHERE b.type LIKE %:title%")
    public List<BooksDTO> findByPartialType(String title);

    @Query(value="SELECT count(*) FROM BooksDTO b WHERE b.type LIKE %:title%")
    
    public Integer countByPartialType(String title);


    @Transactional
    public BooksDTO save(BooksDTO book);
    public void deleteById(String id);
    
}
