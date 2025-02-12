package br.com.tarcisio.api_boleto.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.tarcisio.api_boleto.avro.Boleto;
import br.com.tarcisio.api_boleto.mapper.BoletoMapper;
import br.com.tarcisio.api_boleto.service.BoletoService;

@Component
public class NotificacaoConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoConsumer.class);

    private final BoletoService boletoService;

    public NotificacaoConsumer(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-notificacao}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(@Payload Boleto boleto) {

        LOGGER.info(String.format("Condumindo notificação -> %s", boleto));

        boletoService.atualizar(BoletoMapper.toEntity(boleto));

    }

}
