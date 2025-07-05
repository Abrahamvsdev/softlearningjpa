package com.example.softlearning.applicationcore.entity.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
    protected ArrayList<OrderDetails> shopCart;
    protected Dimensions orderPackage = null;
    protected OrderStatus status; // Estado de la compra
    StringBuilder sb = new StringBuilder();

    protected Order() {
        this.phoneContact = new HashSet<>();
        this.shopCart = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    // ********* ORDER BUILDERS*********
    
    public static Order getInstance(
            String receiverAddress,
            String receiverPerson,
            String idClient,
            String phoneContact) throws BuildException {
        Order o = new Order();

        StringBuilder errors = new StringBuilder();
        int errorCode;

        if ((errorCode = o.setReceiverAddress(receiverAddress)) != 0) {
            errors.append("Bad receiverAddress: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setReceiverPerson(receiverPerson)) != 0) {
            errors.append("Bad receiverPerson: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setIdClient(idClient)) != 0) {
            errors.append("Bad idClient: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if ((errorCode = o.setPhoneContact(phoneContact)) != 0) {
            errors.append("Bad phoneContact: ").append(Check.getErrorMessage(errorCode)).append("\n");
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

        try {
            o.operation(
                    reference,
                    description,
                    initDate
            );
        } catch (BuildException e) {
            throw new BuildException("Error en la operación(try operation): " + e.getMessage());
        }

        try {
            o.orderPackage = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        } catch (BuildException e) {
            throw new BuildException("Error en las dimensiones(try dimensiones): " + e.getMessage());
        }

        if ((errorCode = o.setReceiverAddress(receiverAddress)) != 0) {
            errors.append("Bad receiverAddress: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = o.setReceiverPerson(receiverPerson)) != 0) {
            errors.append("Bad receiverPerson: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = o.setIdClient(idClient)) != 0) {
            errors.append("Bad idClient: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if ((errorCode = o.setPhoneContact(phoneContact)) != 0) {
            errors.append("Bad phoneContact: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }

        if (shopCart != null && !shopCart.isEmpty()) {
            if (o.setShopCartDetails(shopCart) != 0) {
                throw new BuildException("Error al establecer los detalles del carrito");
            }
        }

        if (paymentDate != null) {
            if ((errorCode = o.setPaymentDate(paymentDate)) != 0) {
                errors.append("Bad paymentDate: ").append(Check.getErrorMessage(errorCode)).append("\n");
            }
        }

        try {
            o.setOrderPackage(weight, height, width, fragile, length);
        } catch (BuildException e) {
            throw new BuildException("Error al establecer los detalles del carrito: " + e.getMessage());
            
        }


        if (deliveryDate != null) {
            if ((errorCode = o.setDeliveryDate(deliveryDate)) != 0) {
                errors.append("Bad deliveryDate: ").append(Check.getErrorMessage(errorCode)).append("\n");
            }
        }

        if (finishDate != null) {
            if ((errorCode = o.setOrderFinishDate(finishDate)) != 0) {
                errors.append("Bad finishDate: ").append(Check.getErrorMessage(errorCode)).append("\n");
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

    public int getNumDetails() {
        return this.shopCart.size();
    }

    // setter
    public int setReceiverAddress(String receiverAddress) {
        int errorReceiverAddress = Check.checkLength(receiverAddress, 3, 50);
        if (errorReceiverAddress == 0) {
            this.receiverAddress = receiverAddress;
        }
        return errorReceiverAddress;
    }

    public int setReceiverPerson(String receiverPerson) {
        int errorReceiverPerson = Check.checkLength(receiverPerson, 3, 10);
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

        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("La orden ya fue procesada, no se puede establecer fecha de pago");
        }

        int errorPaymentDate = Check.isValidDateComplete(paymentDate);
        if (errorPaymentDate != 0) {
            return errorPaymentDate;
        }

        this.paymentDate = LocalDateTime.parse(paymentDate, this.formatter);
        this.status = OrderStatus.CONFIRMED;
        return 0;
    }

    public int setIdClient(String idClient) {
        int errorIdClient = Check.checkLength(idClient, 1, 1000);
        if (errorIdClient == 0) {
            this.idClient = idClient;
        }
        return errorIdClient;
    }

    public int setPhoneContact(String Phone) {
        int errorPhoneContact = Check.checkMobilePhone(Phone);
        if (errorPhoneContact == 0) {
            phoneContact.add(Phone);
        }
        return errorPhoneContact;
    }

    public int setOrderPackage(double weight, double height, double width, boolean fragile, double length) throws BuildException {
        // Validar estado
        if (this.status != OrderStatus.CONFIRMED) {
            throw new BuildException("No se puede añadir un paquete a una orden no pagada");
        }
        
        // Crear dimensiones
        try {
            this.orderPackage = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
            this.status = OrderStatus.FORTHCOMMING;
        } catch (BuildException e) {
            throw new BuildException("Error al crear el paquete: " + e.getMessage());
        }

        return 0;
    }

  public int setOrderPackage(String oP) throws BuildException {
    // Validar que oP no sea null ni vacío
    int checkEmpty = Check.isEmpty(oP);
    if (checkEmpty != 0) {
        throw new BuildException("Detalles del paquete vacíos: " + Check.getErrorMessage(checkEmpty));
    }

    // Solo se puede añadir un paquete si el estado es CONFIRMED
    if (this.status != OrderStatus.CONFIRMED) {
        throw new BuildException("No se puede añadir un paquete a una orden no pagada");
    }

    // Inicializar valores por defecto
    double weight = 1;
    double height = 1;
    double width = 1;
    boolean fragile = false;
    double length = 1;

    Set<String> seenKeys = new HashSet<>();

    // Separar los pares clave:valor
    String[] details = oP.split(",");
    for (String detail : details) {
        String[] keyValue = detail.split(":");
        if (keyValue.length != 2) {
            throw new BuildException("Formato inválido en el detalle: " + detail);
        }

        String key = keyValue[0].trim();
        String value = keyValue[1].trim();

        // Validar clave duplicada
        if (!seenKeys.add(key)) {
            throw new BuildException("Clave duplicada en los detalles del paquete: " + key);
        }

        if (key.equals("f")) {
            // fragile debe ser "true" o "false"
            if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                throw new BuildException("Valor inválido para 'f': debe ser true o false");
            }
            fragile = Boolean.parseBoolean(value);
        } else {
            // validar valor numérico double entre 0 y 9
            double numValue;
            try {
                numValue = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new BuildException("Valor inválido para '" + key + "': " + value);
            }

            switch (key) {
                case "h" -> height = numValue;
                case "w" -> width = numValue;
                case "W" -> weight = numValue;
                case "d" -> length = numValue;
                default -> throw new BuildException("Parámetro desconocido: " + key);
            }
        }
    }

    // Crear dimensiones
    try {
        this.orderPackage = Dimensions.getInstanceDimensions(weight, height, width, fragile, length);
        this.status = OrderStatus.FORTHCOMMING;
    } catch (BuildException e) {
        throw new BuildException("Error al crear el paquete: " + e.getMessage());
    }

    return 0;
}



    public int setDeliveryDate(String deliveryDate) throws BuildException {
        int checkEmpty = Check.isEmpty(deliveryDate);
        if (checkEmpty != 0) {
            return checkEmpty;
        }

        if (this.status != OrderStatus.FORTHCOMMING) {
            throw new BuildException("No se puede entregar al transportista sin poner un paquete");
        }

        int errorDeliveryDate = Check.isValidDateComplete(deliveryDate);
        if (errorDeliveryDate != 0) {
            return errorDeliveryDate;
        }

        this.deliveryDate = LocalDateTime.parse(deliveryDate, this.formatter);
        this.status = OrderStatus.DELIVERED;
        return 0;
    }

    public int setShopCartDetails(String detailsString) throws BuildException, ServiceException {

    if (this.status != OrderStatus.CREATED) {
        throw new BuildException("No se puede modificar el carrito en el estado actual de la orden");
    }

    if (Check.isEmpty(detailsString) != 0) {
        throw new BuildException("La cadena de detalles está vacía o es nula");
    }

    // Ejemplo válido:
    // "amount:2,ref:REF001,price:10.0,discount:5.0;amount:1,ref:REF002,price:20.0,discount:0.0"
    String[] detailsArray = detailsString.split(";");

        for (String detailString : detailsArray) {
            int amount = 0;
            String detailRef = "";
            double price = 0.0;
            int discount = 0;

            Set<String> usedKeys = new HashSet<>();

            String[] attributes = detailString.split(",");

            for (String attribute : attributes) {
                String[] keyValue = attribute.split(":");

                if (keyValue.length != 2) {
                    throw new ServiceException("Formato inválido en atributo: " + attribute);
                }

                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                if (!usedKeys.add(key)) {
                    throw new ServiceException("Clave repetida dentro del mismo detalle: " + key);
                }

                switch (key) {
                    case "amount" -> amount = Integer.parseInt(value);
                    case "ref" -> detailRef = value;
                    case "price" -> price = Double.parseDouble(value);
                    case "discount" -> discount = Integer.parseInt(value);
                    default -> throw new ServiceException("Parámetro desconocido: " + key);
                }
            }

            try {
                this.setDetail(amount, detailRef, price, discount);
            } catch (ServiceException e) {
                throw new ServiceException("Error al añadir el detalle: " + e.getMessage());
            }
        }

        return 0;
    }


    public int setOrderFinishDate(String finishDate) throws BuildException {
        if (this.status == OrderStatus.FINISHED) {
            throw new BuildException("La orden ya está finalizada");
        }

        if (this.status != OrderStatus.DELIVERED) {
            throw new BuildException("No se puede añadir una fecha de finalización a una orden que no ha sido entregada");
        }

        int error = super.setFinishDate(finishDate);

        if (error != 0) {
            throw new BuildException("Error al establecer la fecha de finalización: " + Check.getErrorMessage(error));
        }

        this.status = OrderStatus.FINISHED;
        return 0;
    }


    // setters de la clase auxiliar OrderDetarils en el Order
    // *************************************************************************
    // SET DETAIL
    // *************************************************************************
    public String setDetail(int amount, String detailRef, double price, int discount) throws ServiceException {
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede añadir un detalle a una orden ya procesada");
        }

        OrderDetails nuevoDetalle;
        try {
            nuevoDetalle = OrderDetails.getInstance(amount, detailRef, price, discount);
        } catch (ServiceException e) {
            throw new ServiceException("Error al crear el detalle: " + e.getMessage());
        }

        for (OrderDetails existente : shopCart) {
            if (existente.getDetailRef().equals(detailRef)) {
                // Solo actualizamos el amount
                int result = updateDetail(detailRef, amount);
                if (result == 0) {
                    return "Cantidad del detalle existente actualizada";
                } else {
                    throw new ServiceException("No se pudo actualizar la cantidad del detalle existente");
                }
            }
        }

        // No existe, lo añadimos
        shopCart.add(nuevoDetalle);
        return "Detalle añadido al carrito";
    }

    

    // detalle por POSICION
    public String getPosDetail(int pos) {
        if (shopCart == null || shopCart.isEmpty()) {
            return "No hay detalles en la orden";
        }

        if (this.status != OrderStatus.CREATED) {
            return "No se puede mostrar el detalle de una orden que no está en estado 'CREATED'";
        }

        if (pos < 0 || pos >= shopCart.size()) {
            return "No existe el detalle en la posición " + pos;
        }

        return shopCart.get(pos).toString(); 
    }

    // detalle por REFERENCIA
    public String getRefDetail(String ref) {
        if (shopCart == null || shopCart.isEmpty()) {
            return "No se puede mostrar el detalle de una orden sin detalles";
        }

        if (this.status != OrderStatus.CREATED) {
            return "No se puede mostrar la referencia de una orden que no está en estado 'CREATED'";
        }

        int errorCode = Check.checkLength(ref, 1, 10);
        if (errorCode != 0) {
            return "Error en la referencia: " + Check.getErrorMessage(errorCode);
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
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede modificar un detalle de una orden ya procesada");
        }
        if (pos < 0 || pos >= shopCart.size()) {
            throw new ServiceException("Error en pos: Posición inválida");
        }

        int errorCode = shopCart.get(pos).setAmount(amount); 
        if (errorCode != 0) {
            throw new ServiceException("Error en amount: " + Check.getErrorMessage(errorCode));
        }

        return 0;
    }

    // cantidad por REFERENCIA y meter dentro del constructor
    public int updateDetail(String ref, int amount) throws ServiceException {
        if (this.shopCart == null || shopCart.isEmpty()) {
            throw new ServiceException("No se puede modificar un detalle de una orden sin detalles");
        }
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede modificar un detalle de una orden ya procesada");
        }

        int errorCode = Check.checkLength(ref, 1, 10);
        if (errorCode != 0) {
            throw new ServiceException("Error en ref: " + Check.getErrorMessage(errorCode));
        }

        for (OrderDetails detail : shopCart) {
            if (detail.getDetailRef().equals(ref)) {
                errorCode = detail.setAmount(amount);
                if (errorCode != 0) {
                    throw new ServiceException("Error en amount: " + Check.getErrorMessage(errorCode));
                }
                return 0;
            }
        }

        throw new ServiceException("No se encontró un detalle con la referencia: " + ref);
    }


    // detalle por posicion
    public void deleteDetail(int pos) throws ServiceException {
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede eliminar un detalle de una orden ya procesada");
        }

        if (shopCart == null || shopCart.isEmpty()) {
            throw new ServiceException("No se puede eliminar un detalle de una orden sin detalles");
        }

        if (pos < 0 || pos >= shopCart.size()) {
            throw new ServiceException("Error en pos: Posición inválida");
        }

        shopCart.remove(pos);
    }

    // detalle por referencia y meter dentro del constructor
    public String deleteDetail(String ref) throws ServiceException {
        if (this.status != OrderStatus.CREATED) {
            throw new ServiceException("No se puede eliminar un detalle de una orden ya procesada");
        }

        if (shopCart == null || shopCart.isEmpty()) {
            throw new ServiceException("No hay detalles para eliminar");
        }

        int errorCode = Check.checkLength(ref, 1, 10);
        if (errorCode != 0) {
            throw new ServiceException("Error en ref: " + Check.getErrorMessage(errorCode));
        }

        Iterator<OrderDetails> iterator = shopCart.iterator();
        while (iterator.hasNext()) {
            OrderDetails detalle = iterator.next();
            if (detalle.getDetailRef().equals(ref)) {
                iterator.remove();
                return "Detalle " + ref + "eliminado correctamente";
            }
        }

        throw new ServiceException("No se encontró ningún detalle con la referencia: " + ref);
    }


    // precio total
    public double getPrice() {
        double total = 0.0;
        for (OrderDetails detalle : shopCart) {
            total += detalle.calculateSubtotal();
        }
        return total;
    }

    // metodo para mostrar los detalles de la compra
    public String getOrderDetails() {

        sb.setLength(0);
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

        sb.setLength(0);
        sb.append("Complete Order Details: \n");
        sb.append("Receiver Address: ").append(this.receiverAddress).append("\n");
        sb.append("Reference: ").append(this.reference).append("\n");
        sb.append("Receiver Person: ").append(this.receiverPerson).append("\n");
        sb.append("ID Client: ").append(getIdClient()).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append("Phone Contact: ").append(getPhoneContact()).append("\n");
        sb.append("Init Date: ").append(this.getInitDate()).append("\n");
        sb.append("Payment Date: ").append(this.getPaymentDate()).append("\n");
        sb.append("Dimensions: ").append(getOrderPackage() != null ? getOrderPackage().getDimensionstoString() : "").append("\n");
        sb.append("Delivery Date: ").append(this.getDeliveryDate()).append("\n");
        sb.append("Finish Date: ").append(getFinishDate()).append("\n");
        sb.append("Status: ").append(this.getStatus()).append("\n\n");
        sb.append("Shop Cart: \n");
        sb.append(getShopCart()).append("\n");
        sb.append("Total Price: ").append(getPrice()).append("\n");
        return sb.toString();
    }

}
