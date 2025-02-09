package com.example.softlearning.applicationcore.entity.client.persistence;

import java.util.List;
import java.util.Optional;

import com.example.softlearning.applicationcore.entity.client.dtos.ClientDTO;

public interface ClientRepository {

    public Optional<ClientDTO> findById(String id); //Esto en teoría debería ser el Dni según la anotación

    public List<ClientDTO> findByName(String name);

    public List<ClientDTO> findByPartialName(String name);

    public Integer countByPartialName(String name);

    public ClientDTO save(ClientDTO client);
    
    public void deleteById(String id);
}
