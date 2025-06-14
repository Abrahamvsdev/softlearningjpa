package com.example.softlearning.applicationcore.entity.client.appservices;

import org.springframework.stereotype.Service;

import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

@Service
//Trato como ID el DNI, que es la clave primaria de la tabla
public interface ClientServices {
    public String getByIdToJson (String dni) throws ServiceException;
    public String getByIdToXml (String dni) throws ServiceException;
    public String addFromJson (String client) throws ServiceException;
    public String addFromXml (String client) throws ServiceException;
    public String updateOneFromJson(String client) throws ServiceException;
    public String updateOneFromXml(String client) throws ServiceException;
    public void deleteById(String id) throws ServiceException;
}