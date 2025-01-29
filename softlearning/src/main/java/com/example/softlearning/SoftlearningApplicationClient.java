package com.example.softlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.example.softlearning.applicationcore.entity.client.dtos.ClientDTO;
import com.example.softlearning.infrastruture.persistence.jpa.JpaClientRepository;

public class SoftlearningApplicationClient {

	public static void main(String[] args) {
        //El run de abajo es el que inicia Spring, que entra en el contexto de la aplicación
		ApplicationContext context = SpringApplication.run(SoftlearningApplicationClient.class, args);
        ///Iniciar Spring → Obtener repositorio → Operaciones CRUD → Mostrar resultados
        System.out.println("Printing all Clients with ClientRepository");
		//Obtener el repositorio de libros, que es un bean de Spring(como una semilla) y se obtiene del contexto, para meter en la variable repo
        var repo = context.getBean(JpaClientRepository.class);

        System.out.println("\n *****   Clients in the repository   ***** \n");
        repo.findAll().forEach(System.out::println);
	
        System.out.println("\n *****   Clients by name  ***** \n");
		repo.findByName("cliente").forEach(System.out::println); 
		
        System.out.println("\n *****   Add a new Client  ***** \n");
        repo.save(new ClientDTO(
            "Jose", 
            "Perez", 
            "email@gmail.com", 
            "Calle falsa 123", 
            "12345678A", 
            "123456789", 
            12, 
            "Targeta", 
            "asd", 
            "02-03-1990"
        ));
	
        System.out.println("\n *****   Client by partial name  ***** \n"); 
		repo.findByPartialName("Cli").forEach(System.out::println);
		
        System.out.println("\n *****   Update a Java Book  ***** \n");
        repo.save(new ClientDTO(
            "Jose", 
            "Perez", 
            "email@gmail.com", 
            "Calle falsa 123", 
            "12345678A", 
            "123456789", 
            12, 
            "Targeta", 
            "asd", 
            "02-03-1990"
            ));

        System.out.println("\n *****   Client by id   ***** \n");
		repo.findById("1234").ifPresent(System.out::println);
	
        System.out.println("\n *****   Delete a Client  ***** \n");
		repo.deleteById("1234");

		System.out.println("\n *****    Clients in our DB: " + repo.countByPartialName("Cli"));
		
	}

}
