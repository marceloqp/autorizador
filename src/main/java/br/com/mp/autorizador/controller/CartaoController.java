package br.com.mp.autorizador.controller;

import br.com.mp.autorizador.domain.Cartao;
import br.com.mp.autorizador.domain.dto.CartaoResponseDTO;
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
    public ResponseEntity<BigDecimal> findById(@PathVariable("numeroCartao") String numeroCartao) {
        return ResponseEntity.ok(this.service.findByNumeroCartao(numeroCartao));
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDTO> save(@RequestBody Cartao cartao) {
        return ResponseEntity.ok(this.service.save(cartao));
    }

}
