package br.com.mp.autorizador.controller;

import br.com.mp.autorizador.domain.dto.TransacaoDTO;
import br.com.mp.autorizador.service.TransacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TransacaoDTO transacao) {
        return this.service.generateTransaction(transacao);
    }
}
