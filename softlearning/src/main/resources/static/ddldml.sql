-- Crear la base de datos (si no existe) y usarla
CREATE DATABASE IF NOT EXISTS softlearning;

USE softlearning;

-- ========================================================
-- TABLA CLIENTS
-- ========================================================
CREATE TABLE IF NOT EXISTS clients (
    dni VARCHAR(20) NOT NULL,
    name VARCHAR(45),
    surname VARCHAR(45),
    email VARCHAR(100),
    address VARCHAR(100),
    number VARCHAR(20),
    paymentMode VARCHAR(45),
    membershipLevel VARCHAR(45),
    registrationDate VARCHAR(20),
    antiquity INT,
    PRIMARY KEY (dni)
);

-- Insertar datos de ejemplo en la tabla clients (según XML)
INSERT INTO
    clients (
        dni,
        name,
        surname,
        email,
        address,
        number,
        paymentMode,
        membershipLevel,
        registrationDate,
        antiquity
    )
VALUES (
        '12345678D',
        'Juan',
        'Pérez',
        'juan.D.perez@example.com',
        'Calle Falsa 123',
        '600123456',
        'Credit Card',
        'Gold',
        '15-12-2025',
        2
    );

-- ========================================================
-- TABLA BOOKS
-- ========================================================
CREATE TABLE IF NOT EXISTS books (
    ident VARCHAR(10) NOT NULL,
    title VARCHAR(45), -- Nota: este campo almacena el atributo "type" del JSON
    paymethod VARCHAR(45),
    date VARCHAR(20),
    author VARCHAR(45),
    isbn VARCHAR(45) UNIQUE,
    cover VARCHAR(45),
    genre VARCHAR(45),
    editorial VARCHAR(45),
    price DOUBLE,
    discount DOUBLE,
    weight DOUBLE,
    height DOUBLE,
    width DOUBLE,
    length DOUBLE,
    volume DOUBLE,
    delaypay BOOLEAN,
    fragile BOOLEAN,
    page INT,
    PRIMARY KEY (ident)
);

-- Insertar datos de ejemplo en la tabla books (según JSON)
INSERT INTO
    books (
        ident,
        title,
        paymethod,
        date,
        author,
        isbn,
        cover,
        genre,
        editorial,
        price,
        discount,
        weight,
        height,
        width,
        length,
        volume,
        delaypay,
        fragile,
        page
    )
VALUES (
        'B003',
        'Novel',
        'Credit Card',
        '10-10-2025',
        'Dan Brown',
        '978-3-16-148410-0',
        'Hardcover',
        'Fiction',
        'Editorial Ejemplo',
        25.99,
        5.0,
        1.2,
        20.0,
        15.0,
        2.5,
        0.5,
        FALSE,
        FALSE,
        300
    );

-- Consultar los registros insertados
SELECT * FROM clients;

SELECT * FROM books;