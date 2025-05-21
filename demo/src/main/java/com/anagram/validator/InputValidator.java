package com.anagram.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * Essa classe é responsável por validar a entrada antes de gerar os anagramas.
 * Ela verifica se:
 * - a lista não está vazia,
 * - todos os caracteres são letras,
 * - não há letras repetidas.
 */
@Component
public class InputValidator {

    /**
     * Método que faz a validação principal.
     * Joga uma exceção se encontrar algum problema com a entrada.
     * 
     * @param input lista de caracteres a ser validada
     */
    public void validate(List<Character> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Entrada não pode ser vazia");
        }

        Set<Character> seen = new HashSet<>();
        for (Character c : input) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Todos os caracteres devem ser letras");
            }

            if (!seen.add(c)) {
                throw new IllegalArgumentException("Caracteres devem ser distintos");
            }
        }
    }
}
