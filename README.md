# Spring_JPA

Gestión de la persistencia a traves de Spring JPA.

Para este proyecto utilizamos un DTO.

En este proyecto trataremos:

* Separación de código para aportar una arquitectura limpia dividida en capas y entidades principales de negocio

* Creación de un proyecto SpringWeb con las dependencias necesarias para una gestión eficiente

* Conexión a la capa de persistencia a través de application.properties en /resources

* Uso de consultas existentes por defecto, consultas de modificacion y consultas personalizadas

* Casos particulares donde los nombres de los atributos de las entidades Java no coincidan con los campos de las tablas en la persistencia (en este caso particular, MySQL)

* Ejemplos en /resources/static de unos fragmentos de código sql para la creación y consultas de tablas

Queda aun pendiente, el desacoplamiento con la capa JPA, con la gestión de los DTO's y los servicios Rest

*** Añadir un libro ***
metodo POST, aplication/json, response en json también, body->raw->
{
  "ident": "B003", ***Es la PK***
  "price": 25.99,
  "delayPay": false,
  "discount": 5.0,
  "type": "Novel", //que sera el title
  "payMethod": "Credit Card",
  "date": "10-10-2025", //Importante el formato de la fecha así, si no, la validación permitirá añadirlo, y arrojará su correspondiente mensaje de error.
  "author": "Dan Brown",
  "isbn": "978-3-16-148410-0" // ***Es UNIQUE***
  "cover": "Hardcover",
  "page": 300,
  "genre": "Fiction",
  "editorial": "Editorial Ejemplo",
  "weight": 1.2,
  "height": 20.0,
  "width": 15.0,
  "fragile": false,
  "length": 2.5,
  "volume": 0.5
}
*** Borrar libro ***
Como no es necesario enviar body, será neceario el metodo DELETE, y la URL http://localhost:8080/softlearning/books/B003

*** GetBook ***

En este caso, tampoco es necesario enviar body, solo la url como este ejemplo http://localhost:8080/softlearning/books/B003 a través del medtodo GET, devuelve un json con sus cosas de json

*** Update ***
A través del método PUT, en la URL http://localhost:8080/softlearning/books/B003
En este caso si será necesario body->raw->JSON
{
  "ident": "B010",                       <Asegurarse que estos campos coinciden>
  "price": 30.50,
  "delayPay": true,
  "discount": 10.0,
  "type": "Novel Updated",
  "payMethod": "Debit Card",
  "date": "01-01-2025",                  <La fecha en este formato>
  "author": "Dan Brown",
  "isbn": "9783161484100",               <Asegurarse que estos campos coinciden>
  "cover": "Paperback",
  "page": 320,
  "genre": "Fiction",
  "editorial": "Editorial Ejemplo",
  "weight": 1.3,
  "height": 21.0,
  "width": 15.0,
  "fragile": false,
  "length": 2.5,
  "volume": 0.6
}

*********************************************

***Añadir Un Cliente***

