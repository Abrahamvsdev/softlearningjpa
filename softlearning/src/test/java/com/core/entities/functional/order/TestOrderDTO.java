package com.core.entities.functional.order;
import com.example.softlearning.applicationcore.entity.order.model.Order;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;
public class TestOrderDTO {
    public static void main(String[] args) throws Exception, BuildException, ServiceException {

        // GETINSTANCE GRANDE
        // El getInstance "grande" nos permite ir añadiendo setter a setter todos y cada
        // uno de los parámetros que nos faltan. Realizando varias verificaciones sobre
        // acciones que nos deben retornar algun error
        Order pedido;
        try {
                pedido = Order.getInstance(
                    1349,
                    "description",
                    "2023/11/02-10:00:10",
                    "calle falsa 123",
                    "pedrito",
                    "623456789",
                    "ID1234",
                    "2024/11/02-10:00:10",
                    "2023/11/02-10:00:10",
                    "2024/11/02-10:00:10",
                    5.0,
                    2.0,
                    5.0,
                    true,
                    4.2,
                    "amount:2,ref:REF001,price:10.0,discount:5.0;amount:1,ref:REF002,price:20.0,discount:0.0");
            System.out.println(pedido.getCompleteOrderDetails());

            // //testear Mapper
            // OrderDTO odto = OrderMapper.dtoFromOrder(pedido);
            // System.out.println(odto.toString());
            

        // {
        //     System.out.println(" \n Esto es una frase para diferenciar \n");
        // }


            // Order pedidoprueba = OrderMapper.orderFromDTO(odto);
            // System.out.println(pedidoprueba.getCompleteOrderDetails());
            
            
        } catch (BuildException | ServiceException e) {
            System.out.println(e.toString());
        }
    }
}
