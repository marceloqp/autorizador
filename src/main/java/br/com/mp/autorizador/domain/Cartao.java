package br.com.mp.autorizador.domain;

import br.com.mp.autorizador.domain.dto.CartaoResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "cartao")
@Getter
@Setter
@ToString
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
    private BigDecimal saldo;

    public static CartaoResponseDTO mapToResponse(Cartao cartao) {
        return CartaoResponseDTO
                .builder()
                .senha(cartao.getSenha())
                .numeroCartao(cartao.getNumero())
                .build();
    }
}
