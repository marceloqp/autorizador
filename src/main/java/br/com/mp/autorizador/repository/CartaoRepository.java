package br.com.mp.autorizador.repository;

import br.com.mp.autorizador.domain.Cartao;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {

    Optional<Cartao> findByNumero(String numeroCartao);

    @Lock(LockModeType.PESSIMISTIC_READ)
    BigDecimal getSaldoByNumero(String numeroCartao);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "UPDATE cartao c  SET c.saldo = :novoSaldo", nativeQuery = true)
    @Modifying
    void updateSaldo(@Param("novoSaldo") BigDecimal novoSaldo);
}
