package com.anagram.model;

import java.util.List;

/**
 * Classe que representa o corpo da requisição que o usuário envia.
 * Basicamente, ela recebe uma lista de letras distintas para a geração dos anagramas.
 * 
 * Exemplo de JSON:
 * {
 *   "letters": ["a", "b", "c"]
 * }
 */
public class AnagramRequest {

    private List<Character> letters;

    /**
     * Getter das letras enviadas na requisição.
     */
    public List<Character> getLetters() {
        return letters;
    }

    /**
     * Setter das letras para configurar a entrada.
     */
    public void setLetters(List<Character> letters) {
        this.letters = letters;
    }
}
