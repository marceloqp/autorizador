package br.com.mp.autorizador.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransacaoDTO {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
