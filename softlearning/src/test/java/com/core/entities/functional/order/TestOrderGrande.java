package com.core.entities.functional.order;

import com.example.softlearning.applicationcore.entity.order.model.Order;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class TestOrderGrande {
    public static void main(String[] args) throws Exception, BuildException, ServiceException {

        // GETINSTANCE GRANDE
        // El getInstance "grande" nos permite ir añadiendo setter a setter todos y cada
        // uno de los parámetros que nos faltan. Realizando varias verificaciones sobre
        // acciones que nos deben retornar algun error
        try {
            Order order = Order.getInstance(
                
                1234,
                "description",
                "2023/11/02-10:00:10",
                "calle falsa 123",
                "Pedro",
                "623456789",
                "ID1234",
                "2024/11/02-10:00:10",
                "2023/11/02-10:00:10",
                "2024/11/02-10:00:10",
                11.0,
                5.0,
                5.0,
                true,
                5.0,
                "amount:2,ref:REF001,price:10.0,discount:5;amount:1,ref:REF002,price:20.0,discount:0");
            System.out.println(order.getCompleteOrderDetails());
            
        } catch (BuildException | ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
