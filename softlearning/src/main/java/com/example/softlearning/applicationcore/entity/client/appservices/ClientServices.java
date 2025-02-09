package com.example.softlearning.applicationcore.entity.client.appservices;

import org.springframework.stereotype.Service;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

@Service
//Trato como ID el DNI, que es la clave primaria de la tabla
public interface ClientServices {
    public String getByIdToJson (String id) throws ServiceException;
    public String getByIdToXml (String id) throws ServiceException;
    public String addFromJson (String book) throws ServiceException;
    public String addFromXml (String book) throws ServiceException;
    public String updateOneFromJson(String book) throws ServiceException;
    public String updateOneFromXml(String book) throws ServiceException;
    public void deleteById(String id) throws ServiceException;
}