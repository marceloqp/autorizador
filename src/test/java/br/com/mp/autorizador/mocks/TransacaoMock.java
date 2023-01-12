package br.com.mp.autorizador.mocks;

import br.com.mp.autorizador.domain.dto.TransacaoDTO;

import java.math.BigDecimal;

public class TransacaoMock {

    public static TransacaoDTO gerarTransacao(){
        return TransacaoDTO
                .builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.ONE)
                .build();
    }
}
