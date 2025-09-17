package com.sistema.Biblioteca.api.dto;

import com.sistema.Biblioteca.domain.enums.CATEGORY;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class BookDTOResponse { //DTO's são objetos utilizados para transportar dados entre a API e o banco de dados, separar responsabilidades entre as camadas da aplicação e garantir mantenabilidade
//DTO's de Response/Resposta são os retornos do servidor ao cliente/browser

    private Long id;

    @NotBlank(message = "Título do livro não pode ser vazio")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Autor do livro não pode ser vazio")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Ano do livro não pode ser vazio")
    @Pattern(regexp = "\\d{4}", message = "Ano deve ter 4 dígitos")
    @Column(name = "publish_year", nullable = false)
    private String year;

    @Pattern(regexp = "\\d{13}", message = "ISBN deve ter 13 dígitos")
    @NotBlank(message = "ISBN do livro não pode ser vazio")
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "Gênero literário do livro não pode ser vazio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CATEGORY category;

    public BookDTOResponse() {
    }

    public BookDTOResponse(Long id, String title, String author, String year, String isbn, CATEGORY category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }
}
