package br.com.mp.autorizador.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseStackTrace;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GenericRestException.class})
    public ResponseEntity<Object> handleExcpetion(GenericRestException ex, WebRequest request) {
        log.info("Problema na requisição. Message: " + StringUtils.stripToEmpty(ex.getMessage()), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMensagem());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        log.error("Erro na requisição. Message: " + StringUtils.stripToEmpty(ex.getMessage()), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DetalhesErro(HttpStatus.INTERNAL_SERVER_ERROR,
                        Arrays.toString(getRootCauseStackTrace(ex))));
    }

}