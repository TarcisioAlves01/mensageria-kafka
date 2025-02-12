package br.com.tarcisio.api_boleto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tarcisio.api_boleto.entity.BoletoEntity;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {

    Optional<BoletoEntity> findByCodigoBarras(String codigo);

}
