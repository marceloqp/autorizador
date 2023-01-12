package br.com.mp.autorizador.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public final class DetalhesErro implements Serializable {

    private String status;
    private String message;

    public DetalhesErro(String mensagemErro) {
        message = mensagemErro;
    }

    public DetalhesErro(HttpStatus status, String message) {
        this.message = message;
        this.status = status.toString();
    }
}
