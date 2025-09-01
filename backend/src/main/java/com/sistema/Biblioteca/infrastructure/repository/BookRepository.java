package com.sistema.Biblioteca.infrastructure.repository;

import com.sistema.Biblioteca.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
