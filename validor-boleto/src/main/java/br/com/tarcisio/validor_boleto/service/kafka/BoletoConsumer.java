package br.com.tarcisio.validor_boleto.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import br.com.tarcisio.api_boleto.avro.Boleto;
import br.com.tarcisio.validor_boleto.mapper.BoletoMapper;
import br.com.tarcisio.validor_boleto.service.ValidarBoletoService;

@Service
public class BoletoConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);

    private final ValidarBoletoService validorBoleto;

    public BoletoConsumer(ValidarBoletoService validorBoleto) {
        this.validorBoleto = validorBoleto;
    }

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consomeBoleto(Boleto boleto, Acknowledgment ack) {

        LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));

        validorBoleto.validar(BoletoMapper.toEntity(boleto));

        // Remove mensagem da pilha no kafka
        ack.acknowledge();        

    }

    /*
     * implements ConsumerSeekAware
     * 
     * @Override
     * public void onPartitionsAssigned(Map<TopicPartition, Long> assignments,
     * ConsumerSeekCallback callback) {
     * assignments.forEach((t, o) -> callback.seekToEnd(t.topic(), t.partition()));
     * }
     */
}
