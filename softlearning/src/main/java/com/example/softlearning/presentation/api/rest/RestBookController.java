package com.example.softlearning.presentation.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softlearning.applicationcore.entity.book.appservices.BookServices;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

@RestController
@RequestMapping("/softlearning/books")
public class RestBookController {

    /**
     * Represents the service layer for handling business logic related to Books.
     * <p>
     * This field is automatically injected using Spring's dependency injection mechanism (@Autowired).
     * It enables the controller to delegate operations to the BookServices without manual instantiation.
     */
    @Autowired
    BookServices bookServices;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonBookById(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdentToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlBookById(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdentToXml(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newBookFromJson(@RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.addFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> newBookFromXml(@RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.addFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookFromJson(@PathVariable(value = "id") String id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateBookFromXml(@PathVariable(value = "id") String id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") String id) {
        try {
            bookServices.deleteByIdent(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}