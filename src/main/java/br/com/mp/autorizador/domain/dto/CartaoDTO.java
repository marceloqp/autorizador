package br.com.mp.autorizador.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartaoDTO {

    String senha;
    String numeroCartao;
}
