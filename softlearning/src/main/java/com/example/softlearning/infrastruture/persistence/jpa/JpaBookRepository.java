package com.example.softlearning.infrastruture.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;

import jakarta.transaction.Transactional;

@Repository
public interface JpaBookRepository extends JpaRepository<BooksDTO, String> {
    public Optional<BooksDTO> findById(String id);

    public List<BooksDTO> findByName(String title);

    @Query(value="SELECT b FROM BooksDTO b WHERE b.name LIKE %:title%")
    public List<BooksDTO> findByPartialTitle(String title);

    @Query(value="SELECT count(*) FROM BooksDTO b WHERE b.name LIKE %:title%")
    public Integer countByPartialTitle(String title);

    @Transactional
    public BooksDTO save(BooksDTO book);
    public void deleteById(String id);
    
}
