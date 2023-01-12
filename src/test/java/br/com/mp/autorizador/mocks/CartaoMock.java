package br.com.mp.autorizador.mocks;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.CartaoDTO;

public class CartaoMock {

    public static Cartao gerarCartao(){
        return new Cartao(gerarCartaoDto());
    }

    public static CartaoDTO gerarCartaoDto(){
        return CartaoDTO
                .builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();
    }

}
