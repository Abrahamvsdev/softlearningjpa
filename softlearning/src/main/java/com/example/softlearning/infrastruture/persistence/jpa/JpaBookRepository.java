package com.example.softlearning.infrastruture.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.persistence.BookRepository;

import jakarta.transaction.Transactional;
// public Optional<BooksDTO> findByIdent(String ident);

//     public List<BooksDTO> findByTitle(String title);

//     public List<BooksDTO> findByPartialIdent(String title);

//     public Integer countByPartialTitle(String title);

//     public BooksDTO save(BooksDTO book);

//     public void deleteByIdent(String ident);

//     public List<BooksDTO> findAll();

@Repository
public interface JpaBookRepository extends JpaRepository<BooksDTO, String>, BookRepository {
    public Optional<BooksDTO> findByIdent(String ident);

    public List<BooksDTO> findByTitle(String title);

    //la anotación @Query permite hacer consultas personalizadas
    //Se puede hacer consultas con JPQL (Java Persistence Query Language)
    @Query(value="SELECT b FROM BooksDTO b WHERE b.ident LIKE %:title%")
    public List<BooksDTO> findByPartialTitle(String title);

    @Query(value="SELECT count(*) FROM BooksDTO b WHERE b.ident LIKE %:title%")
    //devuelve Integer con I mayúscula, preguntar, y 
    public Integer countByPartialTitle(String title);

    //La anotación transaccional es para que Spring se encargue de abrir y cerrar la transacción
    //Si se apaga o se cae la aplicación, Spring se encarga de cerrar la transacción
    //y mantener la integridad de los datos
    @Transactional
    public BooksDTO save(BooksDTO book);
    public void deleteById(String id);
    
}
