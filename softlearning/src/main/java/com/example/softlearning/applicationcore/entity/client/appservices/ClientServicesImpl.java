package com.example.softlearning.applicationcore.entity.client.appservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.softlearning.applicationcore.entity.client.dtos.ClientDTO;
import com.example.softlearning.applicationcore.entity.client.mappers.ClientMapper;
import com.example.softlearning.applicationcore.entity.client.persistence.ClientRepository;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;


@Controller
public class ClientServicesImpl implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;
    private Serializer<ClientDTO> serializer;


    // public Optional<ClientDTO> fiendById(String ident);

    // public List<ClientDTO> findByTitle(String title);

    // public List<ClientDTO> findByPartialIdent(String title);

    // public Integer countByPartialTitle(String title);

    // public ClientDTO save(ClientDTO book);

    // public void deleteByIdent(String ident);

    // public List<ClientDTO> findAll();

    
    protected ClientDTO getDTO(String id) { 
        return clientRepository.findById(id).orElse(null); // El Optional está definido en el "ClientRepository", que está instanciado arriba
    }


    protected ClientDTO getById(String id) throws ServiceException {
        ClientDTO cdto = this.getDTO(id);

        if ( cdto == null ) {
            throw new ServiceException("client " + id + " not found");
        }
        return cdto;
    }
    
    
    protected ClientDTO checkInputData(String client) throws ServiceException {
        try {
            ClientDTO cdto = (ClientDTO) this.serializer.deserialize(client, ClientDTO.class);
            ClientMapper.clientFromDTO(cdto); 
            return cdto;
        } catch (Exception e) {
            throw new ServiceException("error in the input client data: " + e.getMessage());
        }
    }


    protected ClientDTO newClient(String client) throws ServiceException {
        ClientDTO cdto = this.checkInputData(client);
        
        if (this.getDTO(cdto.getId()) == null) {
            return clientRepository.save(cdto);
        } 
        throw new ServiceException ("client " + cdto.getId() + " already exists");
    }


    protected ClientDTO updateClient(String client) throws ServiceException {
        try {
            ClientDTO cdto = this.checkInputData(client);
            this.getById(cdto.getIdent());
            return clientRepository.save(cdto);
        } catch (ServiceException e) {
            throw e;
        }
    }



    // ****** Implementing the interface methods ******

    
    public String getByIdToJson(String id) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.JSON_CLIENT)
                .serialize(this.getById(id));
    }

    
    public String addFromJson(String client) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return serializer.serialize(this.newClient(client));
    }


    @Override
    public String addFromXml(String client) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.XML_CLIENT);
        return serializer.serialize(this.newClient(client));
    }

    @Override
    public String updateOneFromJson(String client) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return serializer.serialize(this.updateClient(client));
    }


    @Override
    public String updateOneFromXml(String client) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.XML_CLIENT);
        return serializer.serialize(this.updateClient(client));
    }
    
    @Override
    public String getByIdToXml(String ident) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.XML_CLIENT).serialize(this.getById(client));
    }


    @Override
    public void deleteById(String id) throws ServiceException {
        try {
            this.getById(id);
            clientRepository.deleteById(id);
        } catch (ServiceException e) {
            throw e;
        }
    }

    
}
