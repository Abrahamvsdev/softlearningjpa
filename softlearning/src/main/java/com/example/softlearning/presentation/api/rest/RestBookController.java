package com.example.softlearning.presentation.api.rest;

import com.example.softlearning.applicationcore.entity.book.appservices.BookServices;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/softlearning/books")
public class RestBookController {

    @Autowired
    BookServices bookServices;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonBookById(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlBookById(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdToXml(id));
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
    public ResponseEntity<String> updateBookFromJson(@PathVariable(value = "id") Integer id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateBookFromXml(@PathVariable(value = "id") Integer id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") Integer id) {
        try {
            bookServices.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}