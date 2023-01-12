package br.com.mp.autorizador.repository;

import br.com.mp.autorizador.domain.Cartao;
import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {
}
