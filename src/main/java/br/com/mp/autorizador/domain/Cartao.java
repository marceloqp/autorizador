package br.com.mp.autorizador.domain;

import br.com.mp.autorizador.domain.dto.CartaoDTO;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cartao")
@ToString
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String numero;

    @Column(length = 4)
    private String senha;

    @Column
    @Positive
    private BigDecimal saldo;

    public Cartao(CartaoDTO cartaoDTO) {
        this.setNumero(cartaoDTO.getNumeroCartao());
        this.setSenha(cartaoDTO.getSenha());
    }

    public static CartaoDTO mapToResponse(Cartao cartao) {
        return CartaoDTO
                .builder()
                .senha(cartao.getSenha())
                .numeroCartao(cartao.getNumero())
                .build();
    }
}
