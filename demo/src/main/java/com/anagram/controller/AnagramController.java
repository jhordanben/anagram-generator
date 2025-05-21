package com.anagram.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anagram.model.AnagramRequest;
import com.anagram.service.AnagramService;
import com.anagram.validator.InputValidator;

/**
 * Controller que expõe o endpoint REST para gerar anagramas
 * Basicamente, recebe uma lista de letras e devolve todas as combinações possíveis
 */

@RestController
@RequestMapping("/api/anagrams")
public class AnagramController {

    private final AnagramService service;
    private final InputValidator validator;

    /**
     * Construtor onde as dependências (service e validator) são injetadas automaticamente
     * 
     * @param service serviço responsável por gerar os anagramas
     * @param validator responsável por validar a entrada antes de processar
     */
    public AnagramController(AnagramService service, InputValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    /**
     * Endpoint principal da aplicação. Espera receber uma lista de letras (sem repetições)
     * e devolve todos os anagramas possíveis
     * Se a entrada estiver errada (letras repetidas, número, nulo, etc), retorna erro 400 com a mensagem
     * 
     * @param request objeto contendo as letras enviadas pelo usuário
     * @return lista com os anagramas ou mensagem de erro
     */
    
    @PostMapping
    public ResponseEntity<List<String>> generateAnagrams(@RequestBody AnagramRequest request) {
        try {
            validator.validate(request.getLetters());
            List<String> result = service.generateAnagrams(request.getLetters());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of("Erro: " + e.getMessage()));
        }
    }
}
