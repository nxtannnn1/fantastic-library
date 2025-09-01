package com.sistema.Biblioteca.application.service;

import com.sistema.Biblioteca.api.dto.BookDTORequest;
import com.sistema.Biblioteca.api.dto.BookDTOResponse;
import com.sistema.Biblioteca.api.mapper.BookDTORequestMapper;
import com.sistema.Biblioteca.api.mapper.BookDTOResponseMapper;
import com.sistema.Biblioteca.application.formatter.ISBNFormatter;
import com.sistema.Biblioteca.domain.model.Book;
import com.sistema.Biblioteca.exceptions.BookNotFoundException;
import com.sistema.Biblioteca.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ISBNFormatter isbnFormatter;


    public BookService(BookRepository bookRepository, ISBNFormatter isbnFormatter) {
        this.bookRepository = bookRepository;
        this.isbnFormatter = isbnFormatter;
    }


    public BookDTOResponse addBook(BookDTORequest bookDTORequest) {

        String yearToInt = bookDTORequest.getYear();
        if (!yearToInt.matches("\\d{4}")) {
            throw new IllegalArgumentException("Ano deve ter 4 dígitos");
        }

        int yearInt = Integer.parseInt(yearToInt);
        if (yearInt < 1900 || yearInt > 2100) {
            throw new IllegalArgumentException("Ano fora do intervalo permitido");
        }

        String validIsbn = isbnFormatter.toValidIsbn(bookDTORequest.getIsbn());
        bookDTORequest.setIsbn(validIsbn);

        if (bookRepository.existsByIsbn(validIsbn)){
            throw new IllegalArgumentException("ISBN já existente!");
        }


        Book book = BookDTORequestMapper.toEntity(bookDTORequest);
        Book saved = bookRepository.save(book);
        return BookDTOResponseMapper.toDto(saved);
    }

    public List<BookDTOResponse> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTOResponse> bookDTOResponses = new ArrayList<>();
        for (Book book : books) {
            bookDTOResponses.add(BookDTOResponseMapper.toDto(book));
        }
        return bookDTOResponses;
    }

    public BookDTOResponse findBookById(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Livro de id " + id + " não encontrado"));
        return BookDTOResponseMapper.toDto(existingBook);
    }

    public BookDTOResponse editBook(Long id, BookDTORequest bookDTORequest) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro de id " + id + " não encontrado!"));

        String validIsbn = isbnFormatter.toValidIsbn(bookDTORequest.getIsbn());
        bookDTORequest.setIsbn(validIsbn);

        if (bookRepository.existsByIsbn(validIsbn)){
            throw new IllegalArgumentException("ISBN já existente!");
        }

        // Atualiza os campos com os dados do DTORequest
        existingBook.setTitle(bookDTORequest.getTitle());
        existingBook.setAuthor(bookDTORequest.getAuthor());
        existingBook.setYear(bookDTORequest.getYear());
        existingBook.setIsbn(bookDTORequest.getIsbn());
        existingBook.setCategory(bookDTORequest.getCategory());

        Book updatedBook = bookRepository.save(existingBook);
        return BookDTOResponseMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Livro de id " + id + " não encontrado!"));
        bookRepository.delete(existingBook);
    }
}
