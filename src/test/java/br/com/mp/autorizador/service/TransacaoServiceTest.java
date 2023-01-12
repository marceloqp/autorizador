package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.dto.TransacaoDTO;
import br.com.mp.autorizador.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;


class TransacaoServiceTest {

    @InjectMocks
    TransacaoService service;

    @Mock
    CartaoRepository cartaoRepository;

    private TransacaoDTO transacaoDTO;

    @Test
    void generateTransaction() {
    }
}