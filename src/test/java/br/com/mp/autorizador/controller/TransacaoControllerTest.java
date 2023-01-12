package br.com.mp.autorizador.controller;

import br.com.mp.autorizador.annotations.ResourceTests;
import br.com.mp.autorizador.domain.dto.TransacaoDTO;
import br.com.mp.autorizador.mocks.TransacaoMock;
import br.com.mp.autorizador.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ResourceTests(TransacaoController.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TransacaoService transacaoService;

    private TransacaoDTO transacaoDTO;

    private static final String apiUrl = "/transacao/";

    @BeforeEach
    public void init() {
        this.transacaoDTO = TransacaoMock.gerarTransacao();
    }

    @Test
    public void generateTransaction() throws Exception {
        when(this.transacaoService.generateTransaction(transacaoDTO)).thenReturn(ResponseEntity.ok(anyString()));

        this.mockMvc.perform(post(apiUrl).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        verify(this.transacaoService).generateTransaction(transacaoDTO);
        verifyNoMoreInteractions(this.transacaoService);
    }

}