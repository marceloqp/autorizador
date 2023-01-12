package br.com.mp.autorizador.service;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.CartaoDTO;
import br.com.mp.autorizador.exception.CartaoNaoEncontradoException;
import br.com.mp.autorizador.repository.CartaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static br.com.mp.autorizador.domain.Cartao.mapToResponse;

@Service
@Slf4j
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Value("${cartao.saldo-inicial}")
    private BigDecimal saldoInicial;

    @Transactional(readOnly = true)
    public BigDecimal findSaldoByNumeroCartao(String numeroCartao) {

        return repository
                .findByNumero(numeroCartao)
                .map(Cartao::getSaldo)
                .orElseThrow(CartaoNaoEncontradoException::new);
    }

    @Transactional
    public ResponseEntity<CartaoDTO> generateCard(CartaoDTO cartao) {

        return repository
                .findByNumero(cartao.getNumeroCartao())
                .isPresent() ? generateExistsResponse(cartao) : generateNewCard(cartao);


    }

    private ResponseEntity<CartaoDTO> generateExistsResponse(CartaoDTO cartao) {
        return new ResponseEntity<CartaoDTO>(cartao, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<CartaoDTO> generateNewCard(CartaoDTO cartaoDTO) {
        Cartao cartao = new Cartao(cartaoDTO);
        cartao.setSaldo(saldoInicial);
        repository.save(cartao);
       return new ResponseEntity<CartaoDTO>(mapToResponse(cartao), HttpStatus.CREATED);
    }
}
