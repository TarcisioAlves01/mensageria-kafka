package br.com.tarcisio.validor_boleto.service;



import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.tarcisio.validor_boleto.entity.BoletoEntity;
import br.com.tarcisio.validor_boleto.entity.enums.SituacaoBoleto;
import br.com.tarcisio.validor_boleto.mapper.BoletoMapper;
import br.com.tarcisio.validor_boleto.repository.BoletoRepository;
import br.com.tarcisio.validor_boleto.service.kafka.NotificacaoProducer;
import lombok.SneakyThrows;

@Service
public class PagarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    public PagarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
    }

    @SneakyThrows
    public void pagar(BoletoEntity boleto) {
        
        String codigoBarrasNumeros = boleto.getCodigoBarras().replaceAll("[^0-9]", "");

        if (codigoBarrasNumeros.length() > 47) {
            complementarBoletoErro(boleto);
        } else {
            complementarBoletoSuccesso(boleto);
        }

        boletoRepository.save(boleto);
        notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));

    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.ERROR_PAGAMENTO);
    }

    private void complementarBoletoSuccesso(BoletoEntity boleto) {
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.PAGO);
    }
}
