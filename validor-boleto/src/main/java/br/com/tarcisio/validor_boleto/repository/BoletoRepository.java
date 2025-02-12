package br.com.tarcisio.validor_boleto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarcisio.validor_boleto.entity.BoletoEntity;

public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {

}
