package br.com.mp.autorizador.domain.dto;

import br.com.mp.autorizador.domain.Cartao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class CartaoResponseDTO {

    String senha;

    String numeroCartao;
}
