package com.sistema.Biblioteca.domain.enums;

public enum CATEGORY { //Responsável por atualizar o status dos livros, com o objetivo de facilitar o gerenciamento do acervo disponível
    NOVEL("Romance"),
    FANTASY("Fantasia"),
    ACTION("Ação"),
    THRILLER("Mistério"),
    ADVENTURE("Aventura"),
    ETC("Outros");

    private final String description;  //Garante clareza, fluidez e estética ao Front-End

    CATEGORY(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
