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

    public List<BooksDTO> findByType(String title); //el nombre del método debe ser findBy + el nombre del campo en la clase BooksDTO
    //Si el campo se llama type, el método debe ser findByType, pero por parametro se le pasa el title

    //la anotación @Query permite hacer consultas personalizadas
    //Se puede hacer consultas con JPQL (Java Persistence Query Language)

    @Query(value="SELECT b FROM BooksDTO b WHERE b.type LIKE %:title%")//JPQL, no SQL. En el where se pone el nombre del campo de la clase BooksDTO, y por params se le pasa el nombre del campo de la tabla
    public List<BooksDTO> findByPartialType(String title);

    @Query(value="SELECT count(*) FROM BooksDTO b WHERE b.type LIKE %:title%")
    //devuelve Integer con I mayúscula, preguntar, y 
    public Integer countByPartialType(String title);

    //public List<BooksDTO> findAll(); //Devuelve todos los libros

    //La anotación transaccional es para que Spring se encargue de abrir y cerrar la transacción
    //Si se apaga o se cae la aplicación, Spring se encarga de cerrar la transacción
    //y mantener la integridad de los datos
    @Transactional
    public BooksDTO save(BooksDTO book);
    public void deleteById(String id);
    
}
