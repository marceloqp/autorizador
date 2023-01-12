package br.com.mp.autorizador.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoDTO {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
