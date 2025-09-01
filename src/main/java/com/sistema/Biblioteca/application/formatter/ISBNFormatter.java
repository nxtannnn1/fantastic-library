package com.sistema.Biblioteca.application.formatter;

import org.springframework.stereotype.Component;

@Component
public class ISBNFormatter {

    public String toValidIsbn(String isbn) {

        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio");
        }

        String onlyNumbersInIsbn = isbn.replaceAll("\\D", "");

        if (onlyNumbersInIsbn.length() != 13) {
            return "ISBN deve ter no máximo 13 números";
        }
        return onlyNumbersInIsbn;
    }

    public static String toFormatIsbn(String isbn) {
        return isbn.substring(0, 3) + "-" +
                isbn.substring(3, 4) + "-" +
                isbn.substring(4, 6) + "-" +
                isbn.substring(6, 12) + "-" +
                isbn.substring(12);
    }
}
