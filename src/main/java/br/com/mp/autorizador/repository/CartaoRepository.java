package br.com.mp.autorizador.repository;

import br.com.mp.autorizador.domain.Cartao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {
    Optional<Cartao> findByNumero(String numeroCartao);
}
