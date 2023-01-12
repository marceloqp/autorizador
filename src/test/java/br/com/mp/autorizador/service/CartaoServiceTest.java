package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.CartaoDTO;
import br.com.mp.autorizador.mocks.CartaoMock;
import br.com.mp.autorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CartaoServiceTest {

    @InjectMocks
    CartaoService service;

    @Mock
    CartaoRepository repository;

    private Cartao cartao;
    private CartaoDTO cartaoDTO;

    @BeforeEach
    public void init(){
        this.cartao = CartaoMock.gerarCartao();
        this.cartaoDTO = CartaoMock.gerarCartaoDto();
    }

    @Test
    void canFindCardByNumeroCartao() {
    }

    @Test
    void canNotFindCardByNumeroCartao() {
    }

    @Test
    void canCreateCard() {
    }

    @Test
    void canNotCreateCard() {
    }
}