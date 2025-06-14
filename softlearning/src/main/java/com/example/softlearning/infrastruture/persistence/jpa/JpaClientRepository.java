package com.example.softlearning.infrastruture.persistence.jpa;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.softlearning.applicationcore.entity.client.dtos.ClientDTO;
import com.example.softlearning.applicationcore.entity.client.persistence.ClientRepository;

import jakarta.transaction.Transactional;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientDTO, String>, ClientRepository {

    @Override
    public List<ClientDTO> findAll();

    
    public Optional<ClientDTO> findByDni(String dni);

    @Override
    public List<ClientDTO> findByName(String name);

    @Override
    @Query(value="SELECT c FROM ClientDTO c WHERE c.name LIKE %:name%")
    public List<ClientDTO> findByPartialName(String name);

    @Override
    @Query(value="SELECT count(*) FROM ClientDTO c WHERE c.name LIKE %:name%")
    public Integer countByPartialName(String name);
    
    @Transactional
    @Override
    public ClientDTO save(ClientDTO client);
    @Override
    public void deleteById(String dni);
}