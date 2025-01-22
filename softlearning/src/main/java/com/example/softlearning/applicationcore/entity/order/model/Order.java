package com.example.softlearning.applicationcore.entity.order.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.dimensions.Dimensions;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.operations.Operation;



public class Order extends Operation {

    protected String receiverAddress, receiverPerson;
    protected LocalDateTime paymentDate = null, deliveryDate = null; // Fecha de entrega
    protected String idClient; // ID del cliente
    protected Set<String> phoneContact; // Telefonos de contacto
    protected ArrayList<OrderDetails> shopCart = null;
    protected Dimensions orderPackage = null;
    protected OrderStatus status; // Estado de la compra
    StringBuilder sb = new StringBuilder();

    protected Order() {
        this.phoneContact = new HashSet<>();
        this.shopCart = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    // ********* ORDER BUILDERS*********/ (quiere decir que hay mas) ademas, hay que
    // jose tiene en el get instance pequeño (ref,
    // ESTE ES EL GET INSTANCE PEQUEÑO
    public static Order getInstance(
            String receiverAddress,
            String receiverPerson,
            String idClient,
            String phoneContact) throws BuildException {
        Order o = new Order();

        StringBuilder errors = new StringBuilder();
        int errorCode;

        if ((errorCode = o.setReceiverAddress(receiverAddress)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setReceiverPerson(receiverPerson)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setIdClient(idClient)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setPhoneContact(phoneContact)) != 0) {
            errors.append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (errors.length() > 0) {

            throw new BuildException("No es posible crear la compra en el pequeño: \n" + errors.toString());
        }

        return o;

    }

    // ESTE ES EL GET INSTANCE GRANDE
    public static Order getInstance(
            // campos del pequeño
            int reference,
            String description,
            String initDate,
            String receiverAddress,
            String receiverPerson,
            String phoneContact,
            String idClient,
            // campos del grande dice jose que deberian ser mas de 3
            String paymentDate,
            String deliveryDate,
            String finishDate,
            double height,
            double width,
            double weight,
            boolean fragile,
            double length,
            String shopCart) throws BuildException, ServiceException {
        StringBuilder errors = new StringBuilder();
        int errorCode;
        

        Order o = new Order();
        // los otros campos que se validan aqui

        try {
            o.operation(
                reference,
                description,
                initDate,
                null
            );
        } catch (BuildException e) {
            throw new BuildException("Error en la operación(try operation): " + e.getMessage());
        }

        try {
            o.orderPackage = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        } catch (BuildException e) {
            throw new BuildException("Error en las dimensiones(try dimensiones): " + e.getMessage());
        }
        
        if (shopCart != null && !shopCart.isEmpty()) {
            if (o.setShopCartDetails(shopCart) != 0) {
                throw new BuildException("Error al establecer los detalles del carrito");
            }
        }

        if (paymentDate != null) {
            if ((errorCode = o.setPaymentDate(paymentDate)) != 0) {
                errors.append(Check.getErrorMessage(errorCode)).append("\n");
            }
        }

        if (deliveryDate != null) {
            if ((errorCode = o.setDeliveryDate(deliveryDate)) != 0) {
                errors.append(Check.getErrorMessage(errorCode)).append("\n");
            }
        }

        if (finishDate != null) {
            if ((errorCode = o.setOrderFinishDate(finishDate)) != 0) {
                errors.append(Check.getErrorMessage(errorCode)).append("\n");
            }
        }


        if (errors.length() > 0) {
            throw new BuildException("No es posible crear la compra en el grande: \n" + errors.toString());
        }
        return o;
    }
    // getter

    public String getShopCart() {
        if (shopCart == null || shopCart.isEmpty()) {
            return "El carrito está vacío";
        }

        String result = "";
        for (OrderDetails detail : shopCart) {
            result += detail.getDetailstoString() + "\n";
        }
    
        return result;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public String getReceiverPerson() {
        return this.receiverPerson;
    }

    public LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public LocalDateTime getDeliveryDate() {
        return this.deliveryDate;
    }

    public String getIdClient() {
        return this.idClient;
    }

    public String getPhoneContact() {
        return this.phoneContact.toString();
    }

    public Dimensions getOrderPackage() {
        return this.orderPackage;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    // metodo para obtener el num de detalles en el carrito de compras
    public int getNumDetails() {
        return this.shopCart.size();
    }

    // setter

    public int setReceiverAddress(String receiverAddress) {
        // aqui habra que preguntar primero si
        int errorReceiverAddress = Check.minMaxLength(receiverAddress);
        if (errorReceiverAddress == 0) {
            this.receiverAddress = receiverAddress;

        }
        return errorReceiverAddress;
    }

    public int setReceiverPerson(String receiverPerson) {
        int errorReceiverPerson = Check.minMaxLength(receiverPerson);
        if (errorReceiverPerson == 0) {
            this.receiverPerson = receiverPerson;
        }
        return errorReceiverPerson;
    }

    public void shopCartCanceled() throws ServiceException {
        if (this.status == OrderStatus.CREATED) {
            this.shopCart.clear();
            this.status = OrderStatus.CANCELLED;
        } else {
            throw new ServiceException("No se puede cancelar una orden que ya ha sido pagada");
        }
    }

    public int setPaymentDate(String paymentDate) throws BuildException {
        if (paymentDate != null) {
            if (this.status == OrderStatus.CREATED) {
                int errorPaymentDate = Check.isValidDateComplete(paymentDate);
                if (errorPaymentDate == 0) {
                    this.paymentDate = LocalDateTime.parse(paymentDate, this.formatter);
                    this.status = OrderStatus.CONFIRMED;
                    return 0;
                }
                return -1;
            }
            return 0;
        }
        throw new BuildException("No se puede setear una fecha de pago nula");
    }

    public int setIdClient(String idClient) {
        int errorIdClient = Check.minMaxLength(idClient);
        if (errorIdClient == 0) {
            this.idClient = idClient;
        }
        return errorIdClient;
    }

    public int setPhoneContact(String Phone) {
        int errorPhoneContact = Check.checkMobilePhone(Phone); // de momento solo checkeo si tiene 9 digitos, nada mas
        if (errorPhoneContact == 0) {
            phoneContact.add(Phone);// el propio metodo set los separa automaticamente, solo tengo que añadirlo en
                                    // los test
        }
        return errorPhoneContact;
    }

    // este es el setter de orderpackage
    public int setOrderPackage(String oP) throws BuildException {

        if (this.status == OrderStatus.CONFIRMED) {
        
        // importante setear los parametros a 0, para que se puedan crear
        double weight = 0;
        double height = 0;
        double width = 0;
        boolean fragile = false;
        double length = 0;

        // ejemploString packageDetails = "h:202.20,w:202.20,W:202.20,f:true,d:202.20";

        // Dividimos el string por comas
        String[] details = oP.split(",");

        // dividimos cada parte del string por los puntos y usamos un switch para
        // asignar con los setters, keyvalue[0] es la letra y keyvalue[1] son los
        // numeros
        // el getinstace tiene que crear un order package
        for (String detail : details) {
            String[] keyValue = detail.split(":");

            switch (keyValue[0]) {
                case "h" -> height = (Double.parseDouble(keyValue[1])); // se setea así
                case "w" -> width = (Double.parseDouble(keyValue[1]));
                case "W" -> weight = (Double.parseDouble(keyValue[1]));
                case "f" -> fragile = (Boolean.parseBoolean(keyValue[1]));
                case "d" -> length = (Double.parseDouble(keyValue[1]));
                default -> {
                    throw new BuildException("Parametro desconocido: " + keyValue[0]);
                }
            }
            // se prueban las dimensions para ver si se pueden crear, si no, que pete
            try {
                this.orderPackage = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
                this.status = OrderStatus.FORTHCOMMING;
            } catch (BuildException e) {
                throw new BuildException("Error en las dimensiones: " + e.getMessage());
            }
        }
        return 0;
    }
    throw new BuildException("No se puede añadir un paquete a una orden no pagada set order package");
}

    public int setDeliveryDate(String deliveryDate) throws BuildException {
        if (deliveryDate != null) {
            int errorDeliveryDate = Check.isValidDateComplete(deliveryDate);
            if (errorDeliveryDate == 0) {
                this.deliveryDate = LocalDateTime.parse(deliveryDate, this.formatter);
                this.status = OrderStatus.DELIVERED;
                return 0;
            }
            if (this.status != OrderStatus.FORTHCOMMING) {
                throw new BuildException("No se puede entregar al transportista sin poner un paquete");
            }
            return errorDeliveryDate;
        }
        return 0;
    }

    public int setShopCartDetails(String detailsString) throws BuildException, ServiceException {

        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("No se puede modificar el carrito en el estado actual de la orden");
        }

        // Ejemplo de detailsString:
        // "amount:2,ref:REF001,price:10.0,discount:5.0;amount:1,ref:REF002,price:20.0,discount:0.0"

        // Separar cada detalle por punto y coma
        String[] detailsArray = detailsString.split(";");

        for (String detailString : detailsArray) {
            int amount = 0;
            String detailRef = "";
            double price = 0.0;
            double discount = 0.0;

            // Separar atributos por coma
            String[] attributes = detailString.split(",");

            for (String attribute : attributes) {
                String[] keyValue = attribute.split(":");
                switch (keyValue[0]) {
                    case "amount" -> amount = Integer.parseInt(keyValue[1]);
                    case "ref" -> detailRef = keyValue[1];
                    case "price" -> price = Double.parseDouble(keyValue[1]);
                    case "discount" -> discount = Double.parseDouble(keyValue[1]);
                    default -> throw new ServiceException("Parámetro desconocido: " + keyValue[0]);
                }
            }

            // Añadir detalle al shopCart
            try {
                this.setDetail(amount, detailRef, price, discount);
            } catch (ServiceException e) {
                throw new ServiceException("Error al añadir el detalle: " + e.getMessage());
            }
        }

        return 0;
    }

    public int setOrderFinishDate(String finishDate) throws BuildException {
        if (this.status == OrderStatus.DELIVERED) {
            int errorFinishDate = Check.isValidDateComplete(finishDate);
            if (errorFinishDate == 0) {
                this.finishDate = LocalDateTime.parse(finishDate, this.formatter);
                this.status = OrderStatus.FINISHED;
                return 0;
            }
            return errorFinishDate;
        }
        throw new BuildException(
                "No se puede añadir una fecha de finalización a una orden que no ha sido entregada");
    }

    // setters de la clase auxiliar OrderDetarils en el Order
    // *************************************************************************
    // SET DETAIL
    // *************************************************************************

    public String setDetail(int amount, String detailRef, double price, double discount) throws ServiceException {
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede añadir un detalle a una orden ya pagada");
        }
        try {

            OrderDetails detalle = OrderDetails.getInstance(amount, detailRef, price, discount);

            this.shopCart.add(detalle);

        } catch (ServiceException e) {
            throw new ServiceException("Error al crear el detalle: " + e.getMessage());
        }
        // Detalle añadido
        return "Detalle añadido al carrito";
    }

    // detalle por POSICION
    public String getPosDetail(int pos) {
        if (this.status == OrderStatus.CREATED) {
            if (pos >= 0 && pos < shopCart.size()) {
                return this.shopCart.get(pos).toString();
            }
            return "No existe el detalle en la posición " + pos;
        }
        return "No se puede mostrar el detalle de una orden que no ha sido creada";
    }

    // detalle por REFERENCIA
    public String getRefDetail(String ref) { // cambiar nombre porque esto liará
        if(this.shopCart == null) {
            return "No se puede mostrar el detalle de una orden sin detalles";
        }
        for (OrderDetails detail : shopCart) {
            if (detail.getDetailRef().equals(ref)) {
                return detail.toString();
            }
        }
        return "Detalle no encontrado";
    }

    // cantidad por POSICION y meter dentro del constructor
    public int updateDetail(int pos, int amount) throws ServiceException {
        if (this.shopCart == null) {
            throw new ServiceException("No se puede modificar un detalle de una orden sin detalles");
        }
        if (this.status == OrderStatus.CONFIRMED) {
            throw new ServiceException("No se puede modificar un detalle de una orden ya pagada");
        }
        // Validar posición
        if (pos < 0 || pos >= shopCart.size()) {
            throw new ServiceException("Error en pos: Posición inválida");
        }

        int errorCode = Check.range(amount);
        if (errorCode != 0) {
            throw new ServiceException("Error en amount: " + Check.getErrorMessage(errorCode));
        }

        this.shopCart.get(pos).setAmount(amount);
        return 0;
    }

    // cantidad por REFERENCIA y meter dentro del constructor
    public int updateDetail(String ref, int amount) throws ServiceException {
        if (this.shopCart == null) {
            throw new ServiceException("No se puede modificar un detalle de una orden sin detalles");
        }
        if (this.status == OrderStatus.CONFIRMED) {
            throw new ServiceException("No se puede modificar un detalle de una orden ya pagada");
        }
        int errorCode = Check.isNull(ref);
        if (errorCode != 0) {
            throw new ServiceException("Error en ref: " + Check.getErrorMessage(errorCode));
        }

        errorCode = Check.range(amount);
        if (errorCode != 0) {
            throw new ServiceException("Error en amount: " + Check.getErrorMessage(errorCode));
        }

        for (OrderDetails detail : shopCart) {

            if (detail.getDetailRef().equals(ref)) {
                detail.setAmount(amount);
                return 0;
            }
        }
        return errorCode;
    }
    

    // detalle por posicion
    public void deleteDetail(int pos) throws ServiceException {
        if (this.status == OrderStatus.CONFIRMED) {
            throw new ServiceException("No se puede eliminar un detalle de una orden ya pagada");
        }
        if (pos < 0 || pos >= shopCart.size()) {
            throw new ServiceException("Error en pos: Posición inválida");
        }

        this.shopCart.remove(pos);

    }

    // detalle por referencia y meter dentro del constructor
    public void deleteDetail(String ref) throws ServiceException {
        if (this.status == OrderStatus.CONFIRMED) {
            throw new ServiceException("No se puede eliminar un detalle de una orden ya pagada");
        }
        int errorCode = Check.isNull(ref);
        if (errorCode != 0) {
            throw new ServiceException("Error en ref: " + Check.getErrorMessage(errorCode));
        }

        for (OrderDetails detalle : shopCart) {
            if (detalle.getDetailRef().equals(ref)) {
                this.shopCart.remove(detalle);
            }
        }

    }

    // precio total
    public double getPrice() {
        double total = 0.0;
        for (OrderDetails detalle : shopCart) {
            total += (detalle.getPrice() - detalle.getDiscount() )* detalle.getAmount();
        }
        return total;
    }

    // metodo para mostrar los detalles de la compra

    public String getOrderDetails() {

        sb.setLength(0); // sb a 0 para que no se repitan ordenes anteriores
        sb.append("Order Details: \n");
        sb.append("Receiver Address: ").append(this.receiverAddress).append("\n");
        sb.append("Receiver Person: ").append(this.receiverPerson).append("\n");
        sb.append("ID Client: ").append(this.idClient).append("\n");
        sb.append("Phone Contact: ").append(this.phoneContact).append("\n");
        sb.append("Init Date: ").append(this.initDate).append("\n");
        sb.append("Finish Date: ").append(this.finishDate).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append("Reference: ").append(this.reference).append("\n");
        return sb.toString();
    }

    public String getCompleteOrderDetails() {

        sb.setLength(0); // sb a 0 para que no se repitan ordenes anteriores
        sb.append("Complete Order Details: \n");
        sb.append("Receiver Address: ").append(this.receiverAddress).append("\n");
        sb.append("Reference: ").append(this.reference).append("\n");
        sb.append("Receiver Person: ").append(this.receiverPerson).append("\n");
        sb.append("ID Client: ").append(getIdClient()).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append("Phone Contact: ").append(getPhoneContact()).append("\n");
        sb.append("Init Date: ").append(this.getInitDate()).append("\n");
        sb.append("Payment Date: ").append(this.getPaymentDate()).append("\n");
        sb.append("Dimensions: ").append(getOrderPackage().getDimensionstoString()).append("\n");
        sb.append("Delivery Date: ").append(this.getDeliveryDate()).append("\n");
        sb.append("Finish Date: ").append(getFinishDate()).append("\n");
        sb.append("Status: ").append(this.getStatus()).append("\n\n");
        sb.append("Shop Cart: \n");
        sb.append(getShopCart()).append("\n");
        // for (OrderDetails detail : shopCart) {
        //     sb.append("-----------------------------------------------------------------------------------").append("\n");
        //     sb.append(detail.getDetailstoString()).append("\n\n");
        // }
        // sb.append("==================").append("\n");
        sb.append("Total Price: ").append(getPrice()).append("\n");
        return sb.toString();
    }

}



// implementaciones de storable
// borradas las implementaciones de storable, no se usan en ningun momento, ya
// dirá jose cuando usarlas
