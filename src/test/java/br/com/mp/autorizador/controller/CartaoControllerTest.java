package br.com.mp.autorizador.controller;

import br.com.mp.autorizador.annotations.ResourceTests;
import br.com.mp.autorizador.domain.dto.CartaoDTO;
import br.com.mp.autorizador.exception.CartaoNaoEncontradoException;
import br.com.mp.autorizador.mocks.CartaoMock;
import br.com.mp.autorizador.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ResourceTests(CartaoController.class)
class CartaoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CartaoService cartaoService;

    private CartaoDTO cartaoDTO;

    private static final String apiUrl = "/cartoes/";

    @BeforeEach
    public void init(){
        this.cartaoDTO = CartaoMock.gerarCartaoDto();
    }

    @Test
    public void canFindByNumeroCartao() throws Exception {
        when(this.cartaoService.findSaldoByNumeroCartao(anyString())).thenReturn(BigDecimal.TEN);

        this.mockMvc.perform(
                        get(apiUrl + "{numeroCartao}", cartaoDTO.getNumeroCartao()).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
        verify(this.cartaoService).findSaldoByNumeroCartao(cartaoDTO.getNumeroCartao());
        verifyNoMoreInteractions(this.cartaoService);
    }

    @Test
    public void canNotFindByNumeroCartao() throws Exception {
        doThrow(new CartaoNaoEncontradoException()).when(this.cartaoService).findSaldoByNumeroCartao(anyString());

        this.mockMvc.perform(get(apiUrl + "{numeroCartao}", cartaoDTO.getNumeroCartao())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void canGenerateCard() throws Exception {

        when(this.cartaoService.generateCard(cartaoDTO)).thenReturn(new ResponseEntity<CartaoDTO>(cartaoDTO, HttpStatus.ACCEPTED));

        this.mockMvc.perform(post(apiUrl).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoDTO))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        verify(this.cartaoService).generateCard(cartaoDTO);
        verifyNoMoreInteractions(this.cartaoService);
    }

    @Test
    public void canNotGenerateCard() throws Exception {

        when(this.cartaoService.generateCard(cartaoDTO)).thenReturn(new ResponseEntity<CartaoDTO>(cartaoDTO, HttpStatus.UNPROCESSABLE_ENTITY));

        this.mockMvc.perform(post(apiUrl).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoDTO))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity());

        verify(this.cartaoService).generateCard(cartaoDTO);
        verifyNoMoreInteractions(this.cartaoService);
    }

}