package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.Status;
import br.com.mp.autorizador.domain.dto.TransacaoDTO;
import br.com.mp.autorizador.exception.GenericRestException;
import br.com.mp.autorizador.mocks.CartaoMock;
import br.com.mp.autorizador.mocks.TransacaoMock;
import br.com.mp.autorizador.repository.CartaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    @InjectMocks
    TransacaoService service;

    @Mock
    CartaoRepository cartaoRepository;

    private TransacaoDTO transacaoDTO;
    private Cartao cartao;

    @BeforeEach
    public void init() {
        this.transacaoDTO = TransacaoMock.gerarTransacao();
        this.cartao = CartaoMock.gerarCartao();
    }

    @Test
    void generateTransactionSucess() {

        when(this.cartaoRepository.getSaldoByNumero(any())).thenReturn(BigDecimal.valueOf(10L));
        when(this.cartaoRepository.findByNumero(any())).thenReturn(Optional.ofNullable(cartao));

        this.service.generateTransaction(transacaoDTO);

        verify(this.cartaoRepository).getSaldoByNumero(any());
        verify(this.cartaoRepository).updateSaldo(any());
        verify(this.cartaoRepository).findByNumero(any());
        verifyNoMoreInteractions(this.cartaoRepository);
    }

    @Test
    void generateTransactionNoBalance() {

        when(this.cartaoRepository.getSaldoByNumero(any())).thenReturn(BigDecimal.ZERO);
        when(this.cartaoRepository.findByNumero(any())).thenReturn(Optional.ofNullable(cartao));

        GenericRestException exception = assertThrows(GenericRestException.class, () ->  this.service.generateTransaction(transacaoDTO));

        Assertions.assertEquals(Status.SALDO_INSUFICIENTE.name(), exception.getMensagem());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatus());


    }

    @Test
    void generateTransactionWrongPassword() {

        transacaoDTO.setSenhaCartao("4321");
        GenericRestException exception = assertThrows(GenericRestException.class, () ->  this.service.generateTransaction(transacaoDTO));

        Assertions.assertEquals(Status.SENHA_INVALIDA.name(), exception.getMensagem());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatus());
    }

    @Test
    void generateTransactionCardNotFound() {

        GenericRestException exception = assertThrows(GenericRestException.class, () ->  this.service.generateTransaction(transacaoDTO));

        Assertions.assertEquals(Status.CARTAO_INEXISTENTE.name(), exception.getMensagem());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatus());
    }
}