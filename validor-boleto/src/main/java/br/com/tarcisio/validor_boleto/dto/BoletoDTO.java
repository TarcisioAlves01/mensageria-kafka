package br.com.tarcisio.validor_boleto.dto;

import java.time.LocalDateTime;

import br.com.tarcisio.validor_boleto.entity.enums.SituacaoBoleto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoletoDTO {
    private Long id;

    private String codigoBarras;

    private SituacaoBoleto situacaoBoleto;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}
