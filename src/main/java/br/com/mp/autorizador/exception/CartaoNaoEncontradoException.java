package br.com.mp.autorizador.exception;

import org.springframework.http.HttpStatus;

public class CartaoNaoEncontradoException extends RuntimeException {

    public CartaoNaoEncontradoException(String numeroCartao) {
        super("O cartao de número " + numeroCartao + " não foi encontrado(a).");
    }
}