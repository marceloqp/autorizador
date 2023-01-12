package br.com.mp.autorizador.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericRestException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final DetalhesErro detalhesErro;

    public GenericRestException(final HttpStatus httpStatus, final String mensagemErro) {
        super(mensagemErro);
        this.httpStatus = httpStatus;
        detalhesErro = new DetalhesErro(httpStatus, mensagemErro);
    }

}
