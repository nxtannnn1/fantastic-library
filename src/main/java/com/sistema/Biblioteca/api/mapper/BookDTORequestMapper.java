package com.sistema.Biblioteca.api.mapper;

import com.sistema.Biblioteca.api.dto.BookDTORequest;
import com.sistema.Biblioteca.domain.model.Book;

public class BookDTORequestMapper { //Mappers servem para converter objetos de um tipo para outro
    //Objetivo: garantir transparência, clareza de código e organização

    public static Book toEntity(BookDTORequest bookDTORequest) { //Recebe um DTO como argumento e cria uma nova entidade Book
        if (bookDTORequest == null) { //Retorna null caso o argumento seja null, assim evitamos erros de NullPointer
            return null;
        } else {
            Book book = new Book();
            book.setId(bookDTORequest.getId());
            book.setTitle(bookDTORequest.getTitle());
            book.setAuthor(bookDTORequest.getAuthor());
            book.setYear(bookDTORequest.getYear());
            book.setIsbn(bookDTORequest.getIsbn());
            book.setCategory(bookDTORequest.getCategory());
            return book;
        }
    }

    public static BookDTORequest toDTO(Book book) { //Recebe como argumento um objeto da classe Book e cria um novo DTO
        if (book == null) {
            return null;
        } else {
            BookDTORequest bookDTORequest = new BookDTORequest();
            bookDTORequest.setId(book.getId());
            bookDTORequest.setTitle(book.getTitle());
            bookDTORequest.setAuthor(book.getAuthor());
            bookDTORequest.setYear(book.getYear());
            bookDTORequest.setIsbn(book.getIsbn());
            bookDTORequest.setCategory(book.getCategory());
            return bookDTORequest;
        }
    }

}
