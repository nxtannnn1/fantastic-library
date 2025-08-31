package com.sistema.Biblioteca.api.controller;

import com.sistema.Biblioteca.api.dto.BookDTORequest;
import com.sistema.Biblioteca.api.dto.BookDTOResponse;
import com.sistema.Biblioteca.application.service.BookService;
import com.sistema.Biblioteca.exceptions.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Classe responsável por gerir requisições HTTPS, como Get, Put, Post, Delete e retorna elementos .json
@RequestMapping("/books") //URL base
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BookController {

    private final BookService bookService;  //Injeção de dependência via construtor
    public BookController(BookService bookService) { //Conceito de desacoplamento, onde o Spring é responsávle por gerir os Beans (Objetos criados pelo Spring, como Service, Repository, etc), de forma que uma classe A não precise instanciar B
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTOResponse> addBook(@RequestBody BookDTORequest bookDTORequest) { //
        BookDTOResponse created = bookService.addBook(bookDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<BookDTOResponse>> findAllBooks() {
        List<BookDTOResponse> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTOResponse> findBookById(@PathVariable Long id) {
        BookDTOResponse dto = bookService.findBookById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTOResponse> editBook(@PathVariable("id") Long id, @RequestBody BookDTORequest bookDTORequest) {
        BookDTOResponse updated = bookService.editBook(id, bookDTORequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable ("id") Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
