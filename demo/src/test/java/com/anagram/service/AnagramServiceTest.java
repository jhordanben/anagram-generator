package com.anagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.anagram.validator.InputValidator;

/**
 * Classe de testes responsável por validar o funcionamento da lógica de geração de anagramas
 * Aqui eu testo os cenários principais: entrada normal, letra única, entrada inválida, letras duplicadas e lista vazia
 * Também aproveito pra testar a validação junto com o serviço pra garantir que tudo tá funcionando como esperado
 */

class AnagramServiceTest {

    private final AnagramService service = new AnagramService();
    private final InputValidator inputValidator = new InputValidator();

    /**
     * Teste com três letras distintas pra garantir que todos os anagramas possíveis estão sendo gerados corretamente
     */

    @Test
    void testNormalCase() {
        List<Character> input = List.of('a', 'b', 'c');
        inputValidator.validate(input);
        List<String> result = service.generateAnagrams(input);
        List<String> expected = List.of("abc", "acb", "bac", "bca", "cab", "cba");
        assertEquals(6, result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    /**
     * Teste com apenas uma letra pra garantir que a função trata corretamente esse caso mais simples
     */

    @Test
    void testSingleLetter() {
        List<Character> input = List.of('x');
        inputValidator.validate(input);
        List<String> result = service.generateAnagrams(input);
        assertEquals(List.of("x"), result);
    }

    /**
     * Teste com um caractere inválido (número) pra garantir que o validador acusa corretamente
     */

    @Test
    void testInvalidCharacter() {
        List<Character> input = List.of('a', '1');
        Exception exception = assertThrows(IllegalArgumentException.class, 
        () -> inputValidator.validate(input));
        assertEquals("Todos os caracteres devem ser letras", exception.getMessage());
    }

    /**
     * Teste com letras duplicadas pra garantir que o validador recusa esse tipo de entrada
     */

    @Test
    void testDuplicateLetters() {
        List<Character> input = List.of('a', 'a');
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> inputValidator.validate(input));
        assertEquals("Caracteres devem ser distintos", exception.getMessage());
    }

    /**
     * Teste com lista vazia pra garantir que o erro de entrada vazia é tratado corretamente
     */

    @Test
    void testEmptyInput() {
        List<Character> input = List.of();
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> inputValidator.validate(input));
        assertEquals("Entrada não pode ser vazia", exception.getMessage());
    }
}
