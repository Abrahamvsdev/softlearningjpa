package com.core.entities.functional.order;

import com.example.softlearning.applicationcore.entity.order.model.Order;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class TestOrder {

    public static void main(String[] args) throws Exception, BuildException, ServiceException {
        // GETINSTANCE PEQUEÑO
        // El getInstance "pequeño" nos permite ir añadiendo setter a setter todos y
        // cada uno de los parámetros que nos faltan. Realizando varias verificaciones
        // sobre acciones que nos deben retornar algun error

        try {

            Order order = Order.getInstance(
                    "Ejemplo 456",
                    "P. Palotes",
                    "ID0678",
                    "655555555"
            );

            order.getOrderDetails();

            order.setDetail(1, "REF123", 10.00, 0);
  
            // Añadir un contacto de teléfono sin que sea un string(No bypass)
            order.setPhoneContact("655555555");
            order.setPhoneContact("644444444");

            /////////////////////////// AQUI ESTOY AÑADIENDO OBJETOS ----------------->
            // Añadir un detalle al carrito
            order.setDetail(1, "REF124", 10.00, 0);
            order.setDetail(1, "REF125", 10.00, 0);
            // Actualizar un detalle del carrito
            order.updateDetail("REF123", 3);

            // Eliminar un detalle del carrito
           order.deleteDetail(0);
           
            /////////////////////////// AQUI ESTOY AÑADIENDO OBJETOS <-----------------
            // Mostrar el detalle del carrito
            System.out.println(order.getOrderDetails());
            System.out.println(order.getNumDetails());
            System.out.println(order.getShopCart());
            System.out.println(order.getStatus());
            //
            System.out.println(order.getStatus());

            //el
            order.setDetail(1, "REF127", 10.00, 0);
            order.setPaymentDate("2023/11/02-09:00:10");
            System.out.println(order.getShopCart());
            System.out.println(order.getStatus());
            order.setOrderPackage("h:2.20,w:2.20,W:2.20,f:true,d:2.20");
            System.out.println(order.getStatus());
            order.setDeliveryDate("2023/11/12-18:00:10");
            System.out.println(order.getStatus());
            order.setOrderFinishDate("2023/11/12-18:00:10");
            System.out.println(order.getStatus());
            order.updateDetail(0, 1);

        } catch (BuildException e) {
            System.out.println("Error al crear Order pequeño: " + e.getMessage());
        }
    }

    // GETINSTANCE GRANDE
    // try {
    // Set<String> phoneContacts = new HashSet<>();
    // List<OrderDetails> shopCart = new ArrayList<>();
    // // Crear Order con getInstance grande
    // Order order = Order.getInstance(
    // "Ejemplo 456",
    // "Pedro Medario",
    // "ID5678",
    // phoneContacts,
    // "2023/11/01-10:00:00",
    // "2023/11/10-18:00:00",
    // "Descripción del pedido grande",
    // 2002,
    // "2023/11/02-09:00:10", // paymentDate
    // "2023/11/12-17:00:10", // deliveryDate
    // "h:202.20,w:202.20,W:202.20,f:true,d:202.20", // Dimensions
    // shopCart);
    // // Añadir un contacto de teléfono
    // //phoneContacts.add("987654321");
    // order.setPhoneContact("123456789");
    // order.setPhoneContact("987654321");
    // order.setDetail(1, "REF123", 10.00, 0.0);
    // order.setDetail(1, "REF124", 10.00, 0.0);
    // order.setDetail(1, "REF125", 10.00, 0.0);
    // order.setDetail(1, "REF126", 10.00, 0.0);
    // System.out.println(order.getCompleteOrderDetails());
    // System.out.println(order.getNumDetails());
    // System.out.println("Order grande creado exitosamente");
    // order.setDetail(1, "REF127", 10.00, 0.0);
    // order.updateDetail("REF123", 3);
    // order.deleteDetail(0);
    // //*******************PETANDO EL DETAIL */
    // try {
    // order.updateDetail(-1, 15);
    // } catch (ServiceException e) {
    // System.out.println("Error al añadir detalle: " + e.getMessage());
    // }
    // order.updateDetail("REF126", 5);
    // //order.setDeliveryDate("2023/11/12-18:00:10");
    // //order.updateDetail(0, 5 );
    // //*******************PETANDO LA FECHA */
    // try {
    // order.setPaymentDate("2023/11/02-09:00:10");
    // } catch (BuildException e) {
    // System.out.println("Error al añadir la fecha: " + e.getMessage());
    // }
    // System.out.println(order.getPaymentDate());
    // // Crear lista de OrderDetails
    // //shopCart.add(OrderDetails.getInstance(3, "REF002", 20.0, 0.0));
    // } catch (BuildException e) {
    // System.out.println("Error al crear Order grande: " + e.getMessage());
    // } catch (ServiceException e) {
    // System.out.println("Error de servicio: " + e.getMessage());
    // }
    // }
    // Configurar datos básicos
    // shopCart.set(1, OrderDetails.getInstance(2, "REF003", 10.0, 0.0)); para
    // añadirlo en la posicion que yo quiera
    // Eliminar un producto usando el metodo deleteDetail de Order
    // shopCart.deleteDetail(1); // Eliminar un producto
    // eliminar un detalle del carrito con el metodo deleteDetail de Order
    // shopCart.get(0).setAmount(3); Modificar cantidad de un producto
}
