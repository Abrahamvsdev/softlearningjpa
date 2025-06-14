package com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerializer<T> implements Serializer<T> {
    private ObjectMapper mapper = new ObjectMapper();

    public String serialize(T object)throws ServiceException{
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public T deserialize(String source, Class<T> object)throws ServiceException{
        try {
            return mapper.readValue(source, object);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}