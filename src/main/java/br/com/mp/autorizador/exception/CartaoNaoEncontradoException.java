package br.com.mp.autorizador.exception;

import org.springframework.http.HttpStatus;

public class CartaoNaoEncontradoException extends GenericRestException {

    public CartaoNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, "");
    }
}