package com.core.entities.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.softlearning.applicationcore.entity.client.model.Client;
import com.example.softlearning.applicationcore.entity.order.model.Order;
import com.example.softlearning.applicationcore.entity.order.model.OrderStatus;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class OrderTest {
    // Comunes
    private final int validReference = 1234;
    private final String validDescription = "Pedido urgente";
    private final String validInitDate = "2025/05/21-15:30:00";

    // Del pequeño
    private final String validReceiverAddress = "Avenida del Sol 45";
    private final String validReceiverPerson = "Lucía";
    private final String validPhoneContact = "612345678";
    private final String validIdClient = "CLIENTE99";

    // Fechas
    private final String validPaymentDate = "2025/05/22-10:00:00";
    private final String validDeliveryDate = "2025/05/23-14:00:00";
    private final String validFinishDate = "2025/05/24-12:00:00";

    // Dimensiones del paquete
    private final double validHeight = 10.0;
    private final double validWidth = 5.0;
    private final double validWeight = 2.0;
    private final boolean validFragile = true;
    private final double validLength = 20.0;

    // Carrito
    private final String validShopCart = "amount:2,ref:REF001,price:10.0,discount:5";

    private Order validOrderBig;
    private Order validOrderSmall;

     @BeforeEach
    public void setUp() throws BuildException, ServiceException {
        // Orden creada con el constructor pequeño
        validOrderSmall = Order.getInstance(
            validReceiverAddress,
            validReceiverPerson,
            validIdClient,
            validPhoneContact
        );

        // Orden creada con el constructor grande
       validOrderBig = Order.getInstance (
            validReference,
            validDescription,
            validInitDate,
            validReceiverAddress,
            validReceiverPerson,
            validPhoneContact,
            validIdClient,
            validPaymentDate,
            validDeliveryDate,
            validFinishDate,
            validHeight,
            validWidth,
            validWeight,
            validFragile,
            validLength,
            validShopCart
        );
    }

    // ==================== VALIDACIÓN EN CREACIÓN - CONSTRUCTOR PEQUEÑO ====================

    @Test
    public void testOrderSmallCreateOk() {
        try {
            Order order = Order.getInstance(validReceiverAddress, validReceiverPerson, validIdClient, validPhoneContact);
            assertNotNull(order);
        } catch (Exception e) {
            fail("No debería fallar con los campos válidos");
        }
    }

    @Test
    public void testOrderSmallCreateInvalidReceiverAddress() {
        try {
            Order.getInstance("", validReceiverPerson, validIdClient, validPhoneContact);
            fail("Debería fallar por receiverAddress inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el pequeño: \nBad receiverAddress: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderSmallCreateInvalidReceiverPerson() {
        try {
            Order.getInstance(validReceiverAddress, "", validIdClient, validPhoneContact);
            fail("Debería fallar por receiverPerson inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el pequeño: \nBad receiverPerson: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderSmallCreateInvalidIdClient() {
        try {
            Order.getInstance(validReceiverAddress, validReceiverPerson, "", validPhoneContact);
            fail("Debería fallar por idClient inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el pequeño: \nBad idClient: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderSmallCreateInvalidPhoneContact() {
        try {
            Order.getInstance(validReceiverAddress, validReceiverPerson, validIdClient, "123"); 
            fail("Debería fallar por phoneContact inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el pequeño: \nBad phoneContact: El número de teléfono debe ser un número válido de 9 cifras\n", e.getMessage());
        }
    }

    @Test
    public void testOrderSmallCreateMultipleErrors() {
        try {
            Order.getInstance("", "", "", "123");
            fail("Debería fallar con múltiples errores");
        } catch (Exception e) {
            String expected =
                    "No es posible crear la compra en el pequeño: \n" +
                    "Bad receiverAddress: No puede estar vacío\n" +
                    "Bad receiverPerson: No puede estar vacío\n" +
                    "Bad idClient: No puede estar vacío\n" +
                    "Bad phoneContact: El número de teléfono debe ser un número válido de 9 cifras\n";
            assertEquals(expected, e.getMessage());
        }
    }

    // ==================== VALIDACIÓN EN CREACIÓN - CONSTRUCTOR GRANDE ====================

    @Test
    public void testOrderBigAllValid() {
        try {
            Order order = Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            assertNotNull(order);
        } catch (Exception e) {
            fail("No debería lanzar excepción con campos válidos: " + e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidReceiverAddress() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                "", 
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por receiverAddress inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el grande: \nBad receiverAddress: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidReceiverPerson() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                "", 
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por receiverPerson inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el grande: \nBad receiverPerson: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidPhoneContact() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                "123",
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por phoneContact inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el grande: \nBad phoneContact: El número de teléfono debe ser un número válido de 9 cifras\n", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidIdClient() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                "", 
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por idClient inválido");
        } catch (Exception e) {
            assertEquals("No es posible crear la compra en el grande: \nBad idClient: No puede estar vacío\n", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidPaymentDate() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                "fecha-mal", 
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por paymentDate inválida");
        } catch (Exception e) {
            assertEquals("Error al establecer los detalles del carrito: No se puede añadir un paquete a una orden no pagada", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidDeliveryDate() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                "99-99-9999",
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por deliveryDate inválida");
        } catch (Exception e) {
            assertEquals("No se puede añadir una fecha de finalización a una orden que no ha sido entregada", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidFinishDate() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                "invalid-finish-date", 
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                validShopCart
            );
            fail("Debería fallar por finishDate inválida");
        } catch (Exception e) {
            assertEquals("Error al establecer la fecha de finalización: Formato de fecha incorrecto", e.getMessage());
        }
    }

    @Test
    public void testOrderBigCreateInvalidShopCart() {
        try {
            Order.getInstance(
                validReference,
                validDescription,
                validInitDate,
                validReceiverAddress,
                validReceiverPerson,
                validPhoneContact,
                validIdClient,
                validPaymentDate,
                validDeliveryDate,
                validFinishDate,
                validHeight,
                validWidth,
                validWeight,
                validFragile,
                validLength,
                "amount:2,ref:REF001,amount:5" 
            );
            fail("Debería fallar por formato inválido en shopCart");
        } catch (Exception e) {
            assertEquals("Clave repetida dentro del mismo detalle: amount", e.getMessage());
        }
    }



        // ==================== TESTS DE GETTERS ====================

    @Test
    public void testGetReceiverAddressSmall() {
        assertEquals(validReceiverAddress, validOrderSmall.getReceiverAddress());
    }

    @Test
    public void testGetReceiverPersonSmall() {
        assertEquals(validReceiverPerson, validOrderSmall.getReceiverPerson());
    }

    @Test
    public void testGetIdClientSmall() {
        assertEquals(validIdClient, validOrderSmall.getIdClient());
    }

    @Test
    public void testGetPhoneContactSmall() {
        String formattedPhone = "[" + validPhoneContact + "]";
        assertEquals(formattedPhone, validOrderSmall.getPhoneContact());
    }

    @Test
    public void testGetShopCartEmptySmall() {
        assertEquals("El carrito está vacío", validOrderSmall.getShopCart());
    }

    @Test
    public void testGetNumDetailsSmall() {
        assertEquals(0, validOrderSmall.getNumDetails());
    }

    @Test
    public void testGetOrderPackageSmall() {
        assertNull(validOrderSmall.getOrderPackage());
    }

    @Test
    public void testGetPaymentDateSmall() {
        assertNull(validOrderSmall.getPaymentDate());
    }

    @Test
    public void testGetDeliveryDateSmall() {
        assertNull(validOrderSmall.getDeliveryDate());
    }

    @Test
    public void testGetStatusSmall() {
        assertEquals(OrderStatus.CREATED, validOrderSmall.getStatus());
    }

    // ==================== GETTERS DEL CONSTRUCTOR GRANDE ====================

    @Test
    public void testGetReceiverAddressBig() {
        assertEquals(validReceiverAddress, validOrderBig.getReceiverAddress());
    }

    @Test
    public void testGetReceiverPersonBig() {
        assertEquals(validReceiverPerson, validOrderBig.getReceiverPerson());
    }

    @Test
    public void testGetIdClientBig() {
        assertEquals(validIdClient, validOrderBig.getIdClient());
    }

    @Test
    public void testGetPhoneContactBig() {
        String formattedPhone = "[" + validPhoneContact + "]";
        assertEquals(formattedPhone, validOrderSmall.getPhoneContact());
    }

    @Test
    public void testGetNumDetailsBig() {
        assertEquals(1, validOrderBig.getNumDetails());
    }

    @Test
    public void testGetShopCartBig() {
         assertEquals("amount:2, detailRef:REF001, price:10.0, discount:5, subtotal:19.0\n", validOrderBig.getShopCart());

    }

    @Test
    public void testGetOrderPackageBig() {
        assertNotNull(validOrderBig.getOrderPackage());
    }

    @Test
    public void testGetPaymentDateBig() {
        assertNotNull(validOrderBig.getPaymentDate());
    }

    @Test
    public void testGetDeliveryDateBig() {
        assertNotNull(validOrderBig.getDeliveryDate());
    }

    @Test
    public void testGetStatusBig() {
        assertEquals(OrderStatus.FINISHED, validOrderBig.getStatus());
    }



    /* SET RECEIVER ADDRESS */

   @Test
    public void testSetReceiverAddressValid() {
        assertEquals(0, validOrderBig.setReceiverAddress("Calle Mayor 123"));
    }

    @Test
    public void testSetReceiverAddressNull() {
        assertEquals(-1, validOrderBig.setReceiverAddress(null)); 
    }

    @Test
    public void testSetReceiverAddressEmpty() {
        assertEquals(-2, validOrderBig.setReceiverAddress("")); 
    }

    @Test
    public void testSetReceiverAddressWithSpacesOnly() {
        assertEquals(-2, validOrderBig.setReceiverAddress("    ")); 
    }

    @Test
    public void testSetReceiverAddressTooShort() {
        assertEquals(-3, validOrderBig.setReceiverAddress("AB"));
    }

    @Test
    public void testSetReceiverAddressTooLong() {
        String tooLong = "A".repeat(51);
        assertEquals(-7, validOrderBig.setReceiverAddress(tooLong));
    }

    /* SET RECEIVER PERSON */

    @Test
    public void testSetReceiverPersonValid() {
        assertEquals(0, validOrderBig.setReceiverPerson("Antonio"));
    }

    @Test
    public void testSetReceiverPersonNull() {
        assertEquals(-1, validOrderBig.setReceiverPerson(null));
    }

    @Test
    public void testSetReceiverPersonEmpty() {
        assertEquals(-2, validOrderBig.setReceiverPerson("")); 
    }

    @Test
    public void testSetReceiverPersonWithSpacesOnly() {
        assertEquals(-2, validOrderBig.setReceiverPerson("   ")); 
    }

    @Test
    public void testSetReceiverPersonTooShort() {
        assertEquals(-3, validOrderBig.setReceiverPerson("Al"));
    }

    @Test
    public void testSetReceiverPersonTooLong() {
        assertEquals(-7, validOrderBig.setReceiverPerson("Alejandrísimo")); 
    }

    /* SET PAYMENT DATE */

    @Test
    public void testSetPaymentDateValidWhenCreated() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        int result = order.setPaymentDate("2025/06/01-10:00:00");

        assertEquals(0, result);
        assertNotNull(order.getPaymentDate());
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    public void testSetPaymentDateFailsIfAlreadyConfirmed() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setPaymentDate("2025/06/01-10:00:00");

        BuildException e = assertThrows(BuildException.class, () -> {
            order.setPaymentDate("2025/06/02-11:00:00");
        });

        assertEquals("La orden ya fue procesada, no se puede establecer fecha de pago", e.getMessage());
    }

    @Test
    public void testSetPaymentDateEmpty() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        int result = order.setPaymentDate("");
        assertEquals(-2, result);
    }

    @Test
    public void testSetPaymentDateNull() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        int result = order.setPaymentDate(null);
        assertEquals(-1, result); 
    }

    @Test
    public void testSetPaymentDateInvalidFormat() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        int result = order.setPaymentDate("fecha-muy-mal");
        assertEquals(-14, result);
    }



    /* SHOP CART CANCELED */

    @Test
    public void testShopCartCanceledInCreatedState() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);

        assertEquals(OrderStatus.CREATED, order.getStatus());

        order.shopCartCanceled();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertEquals(0, order.getNumDetails());
    }

    @Test
    public void testShopCartCanceledFailsAfterConfirmed() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00"); // cambia a CONFIRMED

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());

        ServiceException e = assertThrows(ServiceException.class, order::shopCartCanceled);
        assertEquals("No se puede cancelar una orden que ya ha sido pagada", e.getMessage());
    }

    @Test
    public void testShopCartCanceledFailsAfterForthcomming() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1.0, 1.0, 1.0, false, 1.0); // cambia a FORTHCOMMING

        assertEquals(OrderStatus.FORTHCOMMING, order.getStatus());

        ServiceException e = assertThrows(ServiceException.class, order::shopCartCanceled);
        assertEquals("No se puede cancelar una orden que ya ha sido pagada", e.getMessage());
    }

    @Test
    public void testShopCartCanceledFailsAfterDelivered() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1.0, 1.0, 1.0, false, 1.0);
        order.setDeliveryDate("2025/06/02-10:00:00"); // cambia a DELIVERED

        assertEquals(OrderStatus.DELIVERED, order.getStatus());

        ServiceException e = assertThrows(ServiceException.class, order::shopCartCanceled);
        assertEquals("No se puede cancelar una orden que ya ha sido pagada", e.getMessage());
    }

    @Test
    public void testShopCartCanceledFailsAfterFinished() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1.0, 1.0, 1.0, false, 1.0);
        order.setDeliveryDate("2025/06/02-10:00:00");
        order.setOrderFinishDate("2025/06/03-10:00:00"); // cambia a FINISHED

        assertEquals(OrderStatus.FINISHED, order.getStatus());

        ServiceException e = assertThrows(ServiceException.class, order::shopCartCanceled);
        assertEquals("No se puede cancelar una orden que ya ha sido pagada", e.getMessage());
    }

    /* SET ID CLIENT */

    @Test
    public void testSetIdClientValid() {
        assertEquals(0, validOrderBig.setIdClient("CLIENT123"));
    }

    @Test
    public void testSetIdClientNull() {
        assertEquals(-1, validOrderBig.setIdClient(null));
    }

    @Test
    public void testSetIdClientEmpty() {
        assertEquals(-2, validOrderBig.setIdClient(""));
    }

    @Test
    public void testSetIdClientWithSpacesOnly() {
        assertEquals(-2, validOrderBig.setIdClient("   "));
    }

    @Test
    public void testSetIdClientTooLong() {
        String tooLong = "A".repeat(1001);
        assertEquals(-7, validOrderBig.setIdClient(tooLong));
    }

    /* SET PHONE CONTACT */

    @Test
    public void testSetPhoneContactValid() {
        assertEquals(0, validOrderBig.setPhoneContact("612345678"));
    }

    @Test
    public void testSetPhoneContactNull() {
        assertEquals(-1, validOrderBig.setPhoneContact(null));
    }

    @Test
    public void testSetPhoneContactEmpty() {
        assertEquals(-2, validOrderBig.setPhoneContact(""));
    }

    @Test
    public void testSetPhoneContactWithSpacesOnly() {
        assertEquals(-2, validOrderBig.setPhoneContact("   "));
    }

    @Test
    public void testSetPhoneContactInvalidFormat() {
        assertEquals(-17, validOrderBig.setPhoneContact("123456789")); // No empieza por 6 o 7
    }

    /* SET setOrderPackage double */

    @Test
    public void testSetOrderPackageDoubleValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        int result = order.setOrderPackage(1.0, 1.0, 1.0, true, 1.0);

        assertEquals(0, result);
        assertEquals(OrderStatus.FORTHCOMMING, order.getStatus());
        assertNotNull(order.getOrderPackage());
    }

    @Test
    public void testSetOrderPackageDoubleInValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        
        try {
            order.setOrderPackage(0, 1.0, 1.0, true, 1.0);
            fail("Deberia dar error por wieght invalido");
        } catch (Exception e) {
            assertEquals("Error al crear el paquete: Not possible to create the dimensions: \nBad weight: El número es más pequeño de lo esperado\n", e.getMessage());
        }

    }

    @Test
    public void testSetOrderPackageDoubleFailsIfNotConfirmed() {
        BuildException ex = assertThrows(BuildException.class, () -> {
            validOrderSmall.setOrderPackage(1.0, 1.0, 1.0, true, 1.0);
        });
        assertEquals("No se puede añadir un paquete a una orden no pagada", ex.getMessage());
    }


     /* SET setOrderPackage string */

    @Test
    public void testSetOrderPackageStringValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        int result = order.setOrderPackage("h:1.0,w:1.0,W:1.0,f:true,d:1.0");

        assertEquals(0, result);
        assertEquals(OrderStatus.FORTHCOMMING, order.getStatus());
        assertNotNull(order.getOrderPackage());
    }

@Test
public void testSetOrderPackageStringDuplicateKey() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:5.0,h:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("Clave duplicada en los detalles del paquete: h", ex.getMessage());
}

@Test
public void testSetOrderPackageStringUnknownParameter() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:5.0,x:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("Parámetro desconocido: x", ex.getMessage());
}

@Test
public void testSetOrderPackageStringInvalidNumber() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:cinco,w:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("Valor inválido para 'h': cinco", ex.getMessage());
}

@Test
public void testSetOrderPackageStringOutOfRangeHigh() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:31.0,w:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("Error al crear el paquete: Not possible to create the dimensions: \nBad height: El número es más grande de lo esperado\n", ex.getMessage());
}

@Test
public void testSetOrderPackageStringOutOfRangeLow() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:-1.0,w:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("Error al crear el paquete: Not possible to create the dimensions: \nBad height: El número es más pequeño de lo esperado\n", ex.getMessage());
}

@Test
public void testSetOrderPackageStringInvalidFragileValue() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    order.setDetail(1, "REF001", 10.0, 0);
    order.setPaymentDate("2025/06/01-10:00:00");

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:5.0,w:3.0,W:2.5,f:maybe,d:1.0");
    });
    assertEquals("Valor inválido para 'f': debe ser true o false", ex.getMessage());
}

@Test
public void testSetOrderPackageStringInvalidState() throws Exception {
    Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
    // no setPaymentDate, estado no CONFIRMED

    BuildException ex = assertThrows(BuildException.class, () -> {
        order.setOrderPackage("h:5.0,w:3.0,W:2.5,f:true,d:1.0");
    });
    assertEquals("No se puede añadir un paquete a una orden no pagada", ex.getMessage());
}


    @Test
    public void testSetOrderPackageStringFailsIfNotConfirmed() {
        BuildException ex = assertThrows(BuildException.class, () -> {
            validOrderSmall.setOrderPackage("h:1.0,w:1.0,W:1.0,f:true,d:1.0");
        });
        assertEquals("No se puede añadir un paquete a una orden no pagada", ex.getMessage());
    }

    @Test
    public void testSetOrderPackageStringEmpty() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderPackage("");
        });
        assertEquals("Detalles del paquete vacíos: No puede estar vacío", ex.getMessage());
    }

    @Test
    public void testSetOrderPackageStringInvalidFormat() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderPackage("h:1.0,w:1.0,W:1.0,malvalor,d:1.0");
        });
        assertTrue(ex.getMessage().startsWith("Formato inválido en el detalle"));
    }

    @Test
    public void testSetOrderPackageStringDuplicateKeys() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderPackage("h:1.0,h:2.0,w:1.0,W:1.0,f:true,d:1.0");
        });
        assertEquals("Clave duplicada en los detalles del paquete: h", ex.getMessage());
    }




    /* setDeliveryDate */

    @Test
    public void testSetDeliveryDateValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1.0, 1.0, 1.0, true, 1.0); // FORTHCOMMING

        int result = order.setDeliveryDate("2025/06/02-12:00:00");

        assertEquals(0, result);
        assertNotNull(order.getDeliveryDate());
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    public void testSetDeliveryDateFailsIfNotForthcomming() {
        BuildException ex = assertThrows(BuildException.class, () -> {
            validOrderSmall.setDeliveryDate("2025/06/02-12:00:00");
        });
        assertEquals("No se puede entregar al transportista sin poner un paquete", ex.getMessage());
    }

    @Test
    public void testSetDeliveryDateEmpty() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        int result = order.setDeliveryDate("");
        assertEquals(-2, result); // vacío
    }

    @Test
    public void testSetDeliveryDateInvalidFormat() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1.0, 1.0, 1.0, true, 1.0);

        int result = order.setDeliveryDate("formato-malo");
        assertEquals(-14, result); // formato inválido
    }


    /* setShopCartDetails */

    @Test
    public void testSetShopCartDetailsValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");

        int result = order.setShopCartDetails("amount:2,ref:REF001,price:10.0,discount:5");

        assertEquals(0, result);
        assertEquals(1, order.getNumDetails());
    }

    @Test
    public void testSetShopCartDetailsFailsIfNotCreated() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setPaymentDate("2025/06/01-10:00:00"); // cambia a CONFIRMED

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setShopCartDetails("amount:2,ref:REF001,price:10.0,discount:5");
        });
        assertEquals("No se puede modificar el carrito en el estado actual de la orden", ex.getMessage());
    }

    @Test
    public void testSetShopCartDetailsEmpty() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setShopCartDetails("");
        });
        assertEquals("La cadena de detalles está vacía o es nula", ex.getMessage());
    }

    @Test
    public void testSetShopCartDetailsWithDuplicateKeys() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");

        ServiceException ex = assertThrows(ServiceException.class, () -> {
            order.setShopCartDetails("amount:2,ref:REF001,amount:5,price:10.0,discount:0");
        });
        assertEquals("Clave repetida dentro del mismo detalle: amount", ex.getMessage());
    }

    @Test
    public void testSetShopCartDetailsWithInvalidFormat() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");

        ServiceException ex = assertThrows(ServiceException.class, () -> {
            order.setShopCartDetails("amount=2,ref:REF001,price:10.0,discount:5");
        });
        assertEquals("Formato inválido en atributo: amount=2", ex.getMessage());
    }

    /* setOrderFinishDate */

    @Test
    public void testSetOrderFinishDateValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1, 1, 1, false, 1);
        order.setDeliveryDate("2025/06/02-10:00:00");

        assertEquals(OrderStatus.DELIVERED, order.getStatus());

        int result = order.setOrderFinishDate("2025/06/03-10:00:00");

        assertEquals(0, result);
        assertEquals(OrderStatus.FINISHED, order.getStatus());
    }

    @Test
    public void testSetOrderFinishDateFailsIfAlreadyFinished() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1, 1, 1, false, 1);
        order.setDeliveryDate("2025/06/02-10:00:00");
        order.setOrderFinishDate("2025/06/03-10:00:00");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderFinishDate("2025/06/04-10:00:00");
        });

        assertEquals("La orden ya está finalizada", ex.getMessage());
    }

    @Test
    public void testSetOrderFinishDateFailsIfNotDelivered() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderFinishDate("2025/06/03-10:00:00");
        });

        assertEquals("No se puede añadir una fecha de finalización a una orden que no ha sido entregada", ex.getMessage());
    }

    @Test
    public void testSetOrderFinishDateFailsIfInvalidDate() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        order.setOrderPackage(1, 1, 1, false, 1);
        order.setDeliveryDate("2025/06/02-10:00:00");

        BuildException ex = assertThrows(BuildException.class, () -> {
            order.setOrderFinishDate("fecha-invalida");
        });

        assertTrue(ex.getMessage().startsWith("Error al establecer la fecha de finalización"));
    }


    /* setDetail */

    @Test
    public void testSetDetailValid() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        String result = order.setDetail(2, "REF001", 15.0, 5);

        assertEquals("Detalle añadido al carrito", result);
        assertEquals(1, order.getNumDetails());
    }

    @Test
    public void testSetDetailUpdatesAmountIfExists() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(2, "REF001", 15.0, 5);
        String result = order.setDetail(5, "REF001", 15.0, 5);

        assertEquals("Cantidad del detalle existente actualizada", result);
        assertEquals(1, order.getNumDetails());
    }

    @Test
    public void testSetDetailFailsIfOrderNotCreated() throws Exception {
        Order order = Order.getInstance("Calle Luna 42", "Ana", "CLIENTE_X", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00"); // cambia estado a CONFIRMED

        ServiceException ex = assertThrows(ServiceException.class, () -> {
            order.setDetail(1, "REF002", 10.0, 0);
        });

        assertEquals("No se puede añadir un detalle a una orden ya procesada", ex.getMessage());
    }

    /* getPosDetail */

    @Test
    public void testGetPosDetailValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(2, "REF001", 10.0, 5);

        String shopCartDetails = order.getShopCart().trim();
        String expected = "amount:2, detailRef:REF001, price:10.0, discount:5, subtotal:19.0";

        String firstDetail = shopCartDetails.split("\n")[0].trim();
        assertEquals(expected, firstDetail);
    }

    @Test
    public void testGetPosDetailNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        assertEquals("No hay detalles en la orden", order.getPosDetail(0));
    }

    @Test
    public void testGetPosDetailInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        assertEquals("No se puede mostrar el detalle de una orden que no está en estado 'CREATED'", order.getPosDetail(0));
    }

    @Test
    public void testGetPosDetailOutOfBounds() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        assertEquals("No existe el detalle en la posición 10", order.getPosDetail(10));
        assertEquals("No existe el detalle en la posición -1", order.getPosDetail(-1));
    }






    /* getRefDetail */

    @Test
    public void testGetRefDetailValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(2, "REF001", 10.0, 5);
        String shopCartDetails = order.getShopCart().trim();
        String expected = "amount:2, detailRef:REF001, price:10.0, discount:5, subtotal:19.0";
        // Busca la línea que contiene REF001
        boolean found = false;
        for (String line : shopCartDetails.split("\n")) {
            if (line.contains("REF001")) {
                assertEquals(expected, line.trim());
                found = true;
                break;
            }
        }
        assertTrue(found, "No se encontró el detalle con REF001 en el carrito");
    }

    @Test
    public void testGetRefDetailNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        assertEquals("No se puede mostrar el detalle de una orden sin detalles", order.getRefDetail("REF001"));
    }

    @Test
    public void testGetRefDetailInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        assertEquals("No se puede mostrar la referencia de una orden que no está en estado 'CREATED'", order.getRefDetail("REF001"));
    }

    @Test
    public void testGetRefDetailInvalidReference() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        String result = order.getRefDetail("");
        assertEquals("Error en la referencia: No puede estar vacío", result);
    }

    @Test
    public void testGetRefDetailNotFound() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        assertEquals("Detalle no encontrado", order.getRefDetail("NOEXISTE"));
    }


    /* updateDetail pos */
    @Test
    public void testUpdateDetailByPositionValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(2, "REF001", 10.0, 0);
        int result = order.updateDetail(0, 5);
        assertEquals(0, result);
    }

    @Test
    public void testUpdateDetailByPositionNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail(0, 5));
        assertEquals("Error en pos: Posición inválida", e.getMessage());
    }

    @Test
    public void testUpdateDetailByPositionInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail(0, 5));
        assertEquals("No se puede modificar un detalle de una orden ya procesada", e.getMessage());
    }

    @Test
    public void testUpdateDetailByPositionInvalidIndex() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail(10, 5));
        assertEquals("Error en pos: Posición inválida", e.getMessage());
    }


    /* updateDetail ref */

    @Test
    public void testUpdateDetailByReferenceValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(3, "REF001", 10.0, 0);
        int result = order.updateDetail("REF001", 10);
        assertEquals(0, result);
    }

    @Test
    public void testUpdateDetailByReferenceNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail("REF001", 10));
        assertEquals("No se puede modificar un detalle de una orden sin detalles", e.getMessage());
    }

    @Test
    public void testUpdateDetailByReferenceInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail("REF001", 5));
        assertEquals("No se puede modificar un detalle de una orden ya procesada", e.getMessage());
    }

    @Test
    public void testUpdateDetailByReferenceInvalidRef() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail("", 5));
        assertEquals("Error en ref: No puede estar vacío", e.getMessage());
    }

    @Test
    public void testUpdateDetailByReferenceNotFound() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.updateDetail("NOEXISTE", 5));
        assertEquals("No se encontró un detalle con la referencia: NOEXISTE", e.getMessage());
    }

     /* deleteDetail pos */

     @Test
    public void testDeleteDetailByPositionValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.deleteDetail(0);
        assertEquals(0, order.getNumDetails());
    }

    @Test
    public void testDeleteDetailByPositionInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail(0));
        assertEquals("No se puede eliminar un detalle de una orden ya procesada", e.getMessage());
    }

    @Test
    public void testDeleteDetailByPositionNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail(0));
        assertEquals("No se puede eliminar un detalle de una orden sin detalles", e.getMessage());
    }

    @Test
    public void testDeleteDetailByPositionInvalidIndex() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail(10));
        assertEquals("Error en pos: Posición inválida", e.getMessage());
    }




      /* deleteDetail ref */
    @Test
    public void testDeleteDetailByReferenceValid() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        String result = order.deleteDetail("REF001");
        assertEquals("Detalle REF001eliminado correctamente", result);
        assertEquals(0, order.getNumDetails());
    }

    @Test
    public void testDeleteDetailByReferenceInvalidStatus() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        order.setPaymentDate("2025/06/01-10:00:00");
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail("REF001"));
        assertEquals("No se puede eliminar un detalle de una orden ya procesada", e.getMessage());
    }

    @Test
    public void testDeleteDetailByReferenceNoDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail("REF001"));
        assertEquals("No hay detalles para eliminar", e.getMessage());
    }

    @Test
    public void testDeleteDetailByReferenceInvalidRef() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail(""));
        assertEquals("Error en ref: No puede estar vacío", e.getMessage());
    }

    @Test
    public void testDeleteDetailByReferenceNotFound() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(1, "REF001", 10.0, 0);
        ServiceException e = assertThrows(ServiceException.class, () -> order.deleteDetail("NOEXISTE"));
        assertEquals("No se encontró ningún detalle con la referencia: NOEXISTE", e.getMessage());
    }

    /* getPrice */

        @Test
    public void testGetPriceEmptyCart() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        assertEquals(0.0, order.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithDetails() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        order.setDetail(2, "REF001", 10.0, 5); // subtotal = 2 * 10 * (1 - 0.05) = 19.0
        order.setDetail(1, "REF002", 20.0, 0); // subtotal = 20.0
        assertEquals(39.0, order.getPrice(), 0.001);
    }

        /* getOrderDetails */

    @Test
    public void testGetOrderDetailsBasic() throws Exception {
        Order order = Order.getInstance("Calle Falsa 123", "Juan", "cliente1", "612345678");
        String details = order.getOrderDetails();
        assertTrue(details.contains("Receiver Address: Calle Falsa 123"));
        assertTrue(details.contains("Receiver Person: Juan"));
        assertTrue(details.contains("ID Client: cliente1"));
        assertTrue(details.contains("Phone Contact: [612345678]"));
    }

      /* getCompleteOrderDetails */

    @Test
    public void testGetCompleteOrderDetails() throws Exception {
        Order order = Order.getInstance(
            1001,
            "Test description",
            "2025/05/20-10:00:00",
            "Calle Falsa 123",
            "Juan",
            "612345678",
            "cliente1",
            "2025/05/21-11:00:00",
            "2025/05/22-12:00:00",
            "2025/05/23-13:00:00",
            10.0,
            5.0,
            2.0,
            false,
            20.0,
            "amount:2,ref:REF001,price:10.0,discount:5"
        );

        String completeDetails = order.getCompleteOrderDetails();
        assertTrue(completeDetails.contains("Complete Order Details:"));
        assertTrue(completeDetails.contains("Receiver Address: Calle Falsa 123"));
        assertTrue(completeDetails.contains("Receiver Person: Juan"));
        assertTrue(completeDetails.contains("ID Client: cliente1"));
        assertTrue(completeDetails.contains("Payment Date:"));
        assertTrue(completeDetails.contains("Dimensions:"));
        assertTrue(completeDetails.contains("Shop Cart:"));
        assertTrue(completeDetails.contains("Total Price: 19.0"));
}

}
