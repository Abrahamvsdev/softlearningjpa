package com.example.softlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.infrastruture.persistence.jpa.JpaBookRepository;

@SpringBootApplication
public class SoftlearningApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SoftlearningApplication.class, args);
        ///Iniciar Spring → Obtener repositorio → Operaciones CRUD → Mostrar resultados
        System.out.println("Printing all books with BookRepository");
		
        var repo = context.getBean(JpaBookRepository.class);

        System.out.println("\n *****   Books in the repository   ***** \n");
        repo.findAll().forEach(System.out::println);
	
        System.out.println("\n *****   Java Books by tittle  ***** \n");
		repo.findByName("java").forEach(System.out::println); 
		
        System.out.println("\n *****   Add a new Java Book  ***** \n");
        repo.save(new BooksDTO(
            "1234",
            10.0,
            false,
            0.0,
            "type",
            "true",
            "02-11-2023",
            "author",
            "1234567891234",
            "cover",
            10,
            "genre",
            "editorial",
            10.0,
            10.0,
            10.0,
            false,
            10.0, 
            0));
	
        System.out.println("\n *****   Java Books by partial tittle  ***** \n"); 
		repo.findByPartialTitle("java").forEach(System.out::println);
		
        System.out.println("\n *****   Update a Java Book  ***** \n");
        repo.save(new BooksDTO(
            "1234",
            10.0,
            false,
            0.0,
            "type",
            "true",
            "02-11-2023",
            "author",
            "1234567891234",
            "cover",
            10,
            "genre",
            "editorial",
            10.0,
            10.0,
            10.0,
            false,
            10.0,
            0));

        System.out.println("\n *****   Books by id   ***** \n");
		repo.findById("1234").ifPresent(System.out::println);
	
        System.out.println("\n *****   Delete a Book  ***** \n");
		repo.deleteById("1234");

        System.out.println("\n *****   Books by id    ***** \n");
		repo.findById("1234").ifPresent(System.out::println);

		System.out.println("\n *****    Java Books avaliables: " + repo.countByPartialTitle("Java"));
		
	}

}
