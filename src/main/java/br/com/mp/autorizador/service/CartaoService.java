package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.CartaoResponseDTO;
import br.com.mp.autorizador.exception.CartaoNaoEncontradoException;
import br.com.mp.autorizador.exception.GenericRestException;
import br.com.mp.autorizador.repository.CartaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional(readOnly = true)
    public BigDecimal findByNumeroCartao(String numeroCartao) {

        return repository
                .findByNumero(numeroCartao)
                .map(Cartao::getSaldo)
                .orElseThrow(() -> new CartaoNaoEncontradoException(numeroCartao));
    }

    @Transactional
    public CartaoResponseDTO save(Cartao cartao) {

        return null;
    }
}
