# Tecnologías utilizadas

- **Java 17+**: Lenguaje principal del backend.
- **Spring Boot 3+**: Framework para desarrollo rápido de aplicaciones Java.
- **Spring Data JPA**: Abstracción para la persistencia y acceso a datos.
- **Maven**: Gestión de dependencias y ciclo de vida del proyecto.
- **MySQL**: Base de datos relacional utilizada en el proyecto.
- **JUnit**: Pruebas unitarias y de integración.
- **REST**: Exposición de servicios web en formato JSON y XML.

# 🌱 Spring JPA - Gestión de Persistencia

Este proyecto demuestra el uso de **Spring JPA** para gestionar la persistencia de datos con una arquitectura limpia y modular.

---

## 📌 Características Principales

- ✅ **Arquitectura modular** con separación por entidades y capas.
- ✅ **Conexión sencilla** a MySQL mediante `application.properties`.
- ✅ **Consultas eficientes**: nativas, personalizadas y de modificación.
- ✅ **Mapeo flexible** entre entidades Java y tablas de la base de datos.
- ✅ Ejemplos **SQL en `/resources/static`** para facilitar la creación de tablas.
- ✅ **Soporte para JSON y XML** en los servicios REST.
- ✅ **.gitignore** actualizado para evitar archivos generados y temporales en el repositorio.

---

## 📂 Estructura del Proyecto

```
softlearningjpa/
├── .gitignore
├── README.md
├── softlearning/
│   ├── HELP.md
│   ├── mvnw / mvnw.cmd
│   ├── pom.xml
│   ├── .mvn/
│   │   └── wrapper/
│   │       ├── maven-wrapper.jar
│   │       ├── maven-wrapper.properties
│   │       └── MavenWrapperDownloader.java
│   ├── src/
│   │   ├── assets/
│   │   │   └── EleccionDelTIpo.png
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── softlearning/
│   │   │   │               ├── ServletInitializer.java
│   │   │   │               ├── SoftlearningApplication.java
│   │   │   │               ├── applicationcore/
│   │   │   │               │   └── entity/
│   │   │   │               │       ├── book/
│   │   │   │               │       │   ├── appservices/
│   │   │   │               │       │   ├── dtos/
│   │   │   │               │       │   ├── mappers/
│   │   │   │               │       │   ├── model/
│   │   │   │               │       │   └── persistence/
│   │   │   │               │       ├── client/
│   │   │   │               │       ├── employee/
│   │   │   │               │       ├── order/
│   │   │   │               │       └── sharedkernel/
│   │   │   │               │           ├── appservices/serializers/
│   │   │   │               │           ├── domainservices/validations/
│   │   │   │               │           ├── marketable/
│   │   │   │               │           └── model/
│   │   │   │               │               ├── dimensions/
│   │   │   │               │               ├── exceptions/
│   │   │   │               │               ├── operations/
│   │   │   │               │               ├── products/
│   │   │   │               │               └── stakeholders/
│   │   │   │               ├── infrastruture/
│   │   │   │               │   └── persistence/jpa/
│   │   │   │               └── presentation/
│   │   │   │                   └── api/rest/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── static/
│   │   │           └── ddldml.sql
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── core/
│   │                   └── entities/
│   │                       ├── book/
│   │                       ├── check/
│   │                       ├── client/
│   │                       ├── dimensions/
│   │                       ├── employee/
│   │                       ├── functional/
│   │                       │   ├── books/
│   │                       │   ├── client/
│   │                       │   ├── order/
│   │                       │   └── services/
│   │                       ├── operations/
│   │                       ├── order/
│   │                       ├── person/
│   │                       └── products/
│   └── target/ (ignorado por git)
```

---

## 🚀 Endpoints Principales

### 📖 Añadir un Libro
- **Método**: `POST`
- **URL**: `/softlearning/books`
- **Formato**: `application/json` o `application/xml`
- **Ejemplo de Body:**

```json
{
  "ident": "B003",
  "price": 25.99,
  "delayPay": false,
  "discount": 5.0,
  "type": "Novel",
  "payMethod": "Credit Card",
  "date": "10-10-2025",
  "author": "Dan Brown",
  "isbn": "978-3-16-148410-0",
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
```

```XML
<book>
    <ident>B003</ident>
    <price>25.99</price>
    <delayPay>false</delayPay>
    <discount>5.0</discount>
    <type>Novel</type>
    <payMethod>Credit Card</payMethod>
    <date>10-10-2025</date>
    <author>Dan Brown</author>
    <isbn>978-3-16-148410-0</isbn>
    <cover>Hardcover</cover>
    <page>300</page>
    <genre>Fiction</genre>
    <editorial>Editorial Ejemplo</editorial>
    <weight>1.2</weight>
    <height>20.0</height>
    <width>15.0</width>
    <fragile>false</fragile>
    <length>2.5</length>
    <volume>0.5</volume>
</book>
```

### 🗑️ Borrar un Libro
- **Método**: `DELETE`
- **URL**: `*.../softlearning/books/B003`

### 🔍 Obtener un Libro
- **Método**: `GET`
- **URL**: `.../softlearning/books/B003`
- **Respuesta**: JSON con los detalles del libro.

### ✏️ Actualizar un Libro
- **Método**: `PUT`
- **URL**: `.../softlearning/books/B003`
- **Ejemplo de Body:**

```json
{
  "ident": "B001",
  "price": 30.50,
  "delayPay": true,
  "discount": 10.0,
  "type": "Novel Updated",
  "payMethod": "Debit Card",
  "date": "01-01-2025",
  "author": "Dan Brown",
  "isbn": "9783161484100",
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
```


```XML
<book>
    <ident>B003</ident>
    <price>25.99</price>
    <delayPay>false</delayPay>
    <discount>5.0</discount>
    <type>Novel</type>
    <payMethod>Credit Card</payMethod>
    <date>10-10-2025</date>
    <author>Dan Brown</author>
    <isbn>978-3-16-148410-0</isbn>
    <cover>Hardcover</cover>
    <page>300</page>
    <genre>Fiction</genre>
    <editorial>Editorial Ejemplo</editorial>
    <weight>1.2</weight>
    <height>20.0</height>
    <width>15.0</width>
    <fragile>false</fragile>
    <length>2.5</length>
    <volume>0.5</volume>
</book>
```
---


## 🔧 Requisitos Previos

- Java 17+
- Spring Boot 3+
- MySQL
- Maven

---

## 🛠 Instalación y Ejecución

```sh
# Clonar el repositorio
git clone https://github.com/Abrahamvsdev/softlearningjpa.git

# Acceder al proyecto
cd ./softlearning/

# Construir el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

---

## 🎯 Contacto
Si tienes preguntas o sugerencias, no dudes en contactarme en www.linkedin.com/in/abrahamvsdev.

📌 **Este proyecto está diseñado para demostrar buenas prácticas en el uso de Spring JPA.**