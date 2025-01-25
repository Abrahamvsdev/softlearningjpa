package com.example.softlearning.infrastruture.persistence.jpa;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.softlearning.applicationcore.entity.client.dtos.ClientDTO;

import jakarta.transaction.Transactional;

@Repository
public class JpaClientRepository extends JpaRepository<ClientDTO, String> {
    public Optional<ClientDTO> findById(String id);

    public List<ClientDTO> findByName(String name);

    @Query(value="SELECT c FROM ClientDTO c WHERE c.name LIKE %:name%")
    public List<ClientDTO> findByPartialName(String name);

    @Query(value="SELECT count(*) FROM ClientDTO c WHERE c.name LIKE %:name%")
    public Integer countByPartialName(String name);

    @Transactional
    public ClientDTO save(ClientDTO client);
    public void deleteById(String id);
}