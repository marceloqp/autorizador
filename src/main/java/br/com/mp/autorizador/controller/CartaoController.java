package br.com.mp.autorizador.controller;

import br.com.mp.autorizador.domain.dto.CartaoDTO;
import br.com.mp.autorizador.service.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService service;

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> findSaldoByNumeroCartao(@PathVariable("numeroCartao") String numeroCartao) {
        return ResponseEntity.ok(this.service.findSaldoByNumeroCartao(numeroCartao));
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> save(@RequestBody CartaoDTO cartao) {
        return this.service.generateCard(cartao);
    }

}
