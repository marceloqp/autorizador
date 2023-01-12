package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.Status;
import br.com.mp.autorizador.domain.dto.TransacaoDTO;
import br.com.mp.autorizador.exception.GenericRestException;
import br.com.mp.autorizador.repository.CartaoRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@AllArgsConstructor
public class TransacaoService {

    private final CartaoRepository cartaoRepository;

    public ResponseEntity<String> generateTransaction(TransacaoDTO transacao) {

        Cartao cartao = validateCard(transacao.getNumeroCartao());

        if (validatePassword(transacao.getSenhaCartao(), cartao.getSenha())) {
            return new ResponseEntity<String>(Status.SENHA_INVALIDA.name(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        BigDecimal saldo = cartaoRepository.getSaldoByNumero(cartao.getNumero());

        if(validateBalance(transacao.getValor(), saldo)){
            updateBalance(saldo.subtract(transacao.getValor()));
        }

        return new ResponseEntity<String>(Status.OK.name(), HttpStatus.CREATED);


    }

    @Transactional
    private void updateBalance(BigDecimal saldo) {
        try {
            cartaoRepository.updateSaldo(saldo);
        } catch (ConstraintViolationException ex) {
            throw new GenericRestException(HttpStatus.UNPROCESSABLE_ENTITY, Status.SALDO_INSUFICIENTE.name());
        }
    }


    private boolean validateBalance(BigDecimal transacao, BigDecimal saldo) {
        return saldo.compareTo(transacao) > 0;
    }

    private boolean validatePassword(String senhaTransacao, String senhaCartao) {
        return StringUtils.equals(senhaTransacao, senhaCartao);
    }

    private Cartao validateCard(String numeroCartao) {
        return cartaoRepository
                .findByNumero(numeroCartao)
                .orElseThrow(() -> new GenericRestException(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                Status.CARTAO_INEXISTENTE.name()
                        )
                );
    }
}
