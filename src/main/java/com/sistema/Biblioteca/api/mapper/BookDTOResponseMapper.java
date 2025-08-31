package com.sistema.Biblioteca.api.mapper;

import com.sistema.Biblioteca.api.dto.BookDTOResponse;
import com.sistema.Biblioteca.domain.model.Book;

public class BookDTOResponseMapper { //Mappers servem para converter objetos de um tipo para outro
    //Objetivo: garantir transparência, clareza de código e organização

    public static Book toEntity(BookDTOResponse bookDTOResponse) { //Recebe um DTO como argumento e cria uma nova entidade Caminhão
        if (bookDTOResponse == null) { //Retorna null caso o argumento seja null, assim evitamos erros de NullPointer
            return null;
        } else {
            Book book = new Book();
            book.setId(bookDTOResponse.getId());
            book.setTitle(bookDTOResponse.getTitle());
            book.setAuthor(bookDTOResponse.getAuthor());
            book.setYear(bookDTOResponse.getYear());
            book.setIsbn(bookDTOResponse.getIsbn());
            book.setCategory(bookDTOResponse.getCategory());
            return book;
        }
    }

    public static BookDTOResponse toDto(Book book) { //Recebe como argumento um objeto da classe Book e cria um novo DTO
        if (book == null) {
            return null;
        } else {
            BookDTOResponse bookDTOResponse = new BookDTOResponse();
            bookDTOResponse.setId(book.getId());
            bookDTOResponse.setTitle(book.getTitle());
            bookDTOResponse.setAuthor(book.getAuthor());
            bookDTOResponse.setYear(book.getYear());
            bookDTOResponse.setIsbn(book.getIsbn());
            bookDTOResponse.setCategory(book.getCategory());
            return bookDTOResponse;
        }
    }


}
