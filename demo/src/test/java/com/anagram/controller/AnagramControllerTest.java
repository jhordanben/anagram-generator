package com.anagram.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.anagram.model.AnagramRequest;
import com.anagram.service.AnagramService;
import com.anagram.validator.InputValidator;

/**
 * Classe de teste da camada de controller.
 * Aqui a gente testa se o endpoint "/api/anagrams" está funcionando corretamente.
 * 
 * Os testes cobrem dois cenários:
 * - Quando a entrada é válida e o serviço retorna os anagramas corretamente
 * - Quando a entrada é inválida e o sistema responde com erro (400)
 */
@SpringBootTest
@AutoConfigureMockMvc
class AnagramControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnagramService service;

    @MockBean
    private InputValidator validator;

    /**
     * Teste para verificar se o endpoint responde corretamente com entrada válida.
     */
    @Test
    void testGenerateAnagrams_ValidInput() throws Exception {
        AnagramRequest request = new AnagramRequest();
        request.setLetters(List.of('a', 'b'));

        when(service.generateAnagrams(List.of('a', 'b'))).thenReturn(List.of("ab", "ba"));

        mockMvc.perform(post("/api/anagrams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"letters\":[\"a\",\"b\"]}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0]").value("ab"))
            .andExpect(jsonPath("$[1]").value("ba"));
    }

    /**
     * Teste para garantir que, se a entrada tiver caracteres inválidos (tipo números),
     * o sistema retorna erro 400 com a mensagem certa.
     */
    @Test
    void testGenerateAnagrams_InvalidInput() throws Exception {
        AnagramRequest request = new AnagramRequest();
        request.setLetters(List.of('a', '1'));

        doThrow(new IllegalArgumentException("Todos os caracteres devem ser letras."))
            .when(validator).validate(List.of('a', '1'));

        mockMvc.perform(post("/api/anagrams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"letters\":[\"a\",\"1\"]}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0]").value("Erro: Todos os caracteres devem ser letras."));
    }
}
