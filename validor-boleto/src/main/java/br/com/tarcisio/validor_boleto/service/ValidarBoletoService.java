package br.com.tarcisio.validor_boleto.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.tarcisio.validor_boleto.entity.BoletoEntity;
import br.com.tarcisio.validor_boleto.entity.enums.SituacaoBoleto;
import br.com.tarcisio.validor_boleto.mapper.BoletoMapper;
import br.com.tarcisio.validor_boleto.repository.BoletoRepository;
import br.com.tarcisio.validor_boleto.service.kafka.NotificacaoProducer;

@Service
public class ValidarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    private final PagarBoletoService pagarBoletoService;

    public ValidarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer,
            PagarBoletoService pagarBoletoService) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
        this.pagarBoletoService = pagarBoletoService;
    }

    public void validar(BoletoEntity boleto) {

        var codigo = Integer.parseInt(boleto.getCodigoBarras().substring(0,1));

        if (codigo % 2 == 0) {
            complementarBoletoErro(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
        } else {
            complementarBoletoSucesso(boleto);
            boletoRepository.save(boleto);
            notificacaoProducer.enviarMensagem(BoletoMapper.toAvro(boleto));
            pagarBoletoService.pagar(boleto);
        }
        
    }

    private void complementarBoletoErro(BoletoEntity boleto) {
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.ERROR_VALIDACAO);
    }

    private void complementarBoletoSucesso(BoletoEntity boleto) {
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.VALIDADO);
    }
}
