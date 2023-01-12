package br.com.mp.autorizador.mocks;

import br.com.mp.autorizador.domain.dto.TransacaoDTO;

public class TransacaoMock {

    public static TransacaoDTO gerarTransacao(){
        return TransacaoDTO.builder().build();
    }
}
