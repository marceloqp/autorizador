package br.com.mp.autorizador.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericRestException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String mensagem;

    public GenericRestException(final HttpStatus httpStatus, final String mensagemErro) {
        super(mensagemErro);
        this.httpStatus = httpStatus;
        this.mensagem = mensagemErro;
    }

}
