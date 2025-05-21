package com.anagram.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerar os anagramas.
 * A ideia aqui é pegar uma lista de letras distintas e devolver todas
 * as combinações possíveis (sem repetir nenhuma letra dentro da mesma palavra).
 */
@Service
public class AnagramService {

    /**
     * Gera todos os anagramas possíveis a partir de uma lista de letras distintas.
     * Usa um algoritmo de backtracking que monta as palavras testando todas as ordens possíveis.
     * 
     * @param input lista de letras únicas (sem repetição)
     * @return lista com todas as combinações possíveis (anagramas)
     */
    public List<String> generateAnagrams(List<Character> input) {
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[input.size()];
        backtrack(input, new StringBuilder(), used, result);
        return result;
    }

    /**
     * Função auxiliar de backtracking. Vai montando os anagramas letra por letra,
     * marcando quais já foram usadas pra não repetir dentro da mesma palavra.
     * 
     * @param input lista original de letras
     * @param current string em construção
     * @param used array que controla quais letras já foram usadas
     * @param result lista final de anagramas
     */
    private void backtrack(List<Character> input, StringBuilder current, boolean[] used, 
                           List<String> result) {
        if (current.length() == input.size()) {
            result.add(current.toString());
            return;
        }

        for (int i = 0; i < input.size(); i++) {
            if (used[i]) continue;

            used[i] = true;
            current.append(input.get(i));
            backtrack(input, current, used, result);
            current.deleteCharAt(current.length() - 1); // desfaz o passo anterior
            used[i] = false;
        }
    }
}
