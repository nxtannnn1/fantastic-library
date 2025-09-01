package com.sistema.Biblioteca.domain.model;

import com.sistema.Biblioteca.domain.enums.CATEGORY;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity //Declaração de entidade no Banco de Dados
public class Book {

    @Id //Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incrementação de Identificador Global no Banco de Dados
    private Long id;

    @NotBlank(message = "Título do livro não pode ser vazio")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Autor do livro não pode ser vazio")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Ano do livro não pode ser vazio")
    @Column(name = "publish_year", nullable = false)
    private String year;

    @NotBlank(message = "ISBN do livro não pode ser vazio")
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "Gênero literário do livro não pode ser vazio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CATEGORY category;

    public Book() { //Construtor sem parâmetros
    }

    public Book(Long id, String title, String author, String year, String isbn, CATEGORY category) { //Construtor com todos os parâmetros
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.category = category;
    }

    //Com parâmetro: controle, garante que o objeto já vem inicializado
    //Sem parâmetro: flexibilidade, útil quando não sabemos os dados

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
