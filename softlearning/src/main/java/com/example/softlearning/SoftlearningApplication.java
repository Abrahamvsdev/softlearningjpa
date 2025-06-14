package com.example.softlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoftlearningApplication {

    public static void main(String[] args) {
        //El run de abajo es el que inicia Spring, que entra en el contexto de la aplicación
        SpringApplication.run(SoftlearningApplication.class, args);
        ///Iniciar Spring → Obtener repositorio → Operaciones CRUD → Mostrar resultados
        System.out.println("** SPRING INICIADO **");

        //     System.out.println("Printing all books with BookRepository");
        // 	//Obtener el repositorio de libros, que es un bean de Spring(como una semilla) y se obtiene del contexto, para meter en la variable repo
        //     var repo = context.getBean(JpaBookRepository.class);
        //     System.out.println("\n *****   Books in the repository   ***** \n");
        //     repo.findAll().forEach(System.out::println);
        //     System.out.println("\n *****   Java Books by tittle  ***** \n");
        // 	repo.findByIdent("1234").forEach(System.out::println); 
        //     System.out.println("\n *****   Add a new Java Book  ***** \n");
        //     repo.save(new BooksDTO(
        //         "1234",
        //         10.0,
        //         false,
        //         0.0,
        //         "type",
        //         "true",
        //         "02-11-2023",
        //         "author",
        //         "1234567891234",
        //         "cover",
        //         10,
        //         "genre",
        //         "editorial",
        //         10.0,
        //         10.0,
        //         10.0,
        //         false,
        //         10.0, 
        //         0));
        //     System.out.println("\n *****   Java Books by partial tittle  ***** \n"); 
        // 	repo.findByPartialIdent("12").forEach(System.out::println);
        //     System.out.println("\n *****   Update a Java Book  ***** \n");
        //     repo.save(new BooksDTO(
        //         "1234",
        //         10.0,
        //         false,
        //         0.0,
        //         "type",
        //         "true",
        //         "02-11-2023",
        //         "author",
        //         "1234567891234",
        //         "cover",
        //         10,
        //         "genre",
        //         "editorial",
        //         10.0,
        //         10.0,
        //         10.0,
        //         false,
        //         10.0,
        //         0));
        //     System.out.println("\n *****   Books by id   ***** \n");
        // 	repo.findById("1234").ifPresent(System.out::println);
        //     System.out.println("\n *****   Delete a Book  ***** \n");
        // 	repo.deleteById("1234");
        // 	System.out.println("\n *****    Java Books avaliables: " + repo.countByPartialTitle("Java"));
        // }
    }
}
