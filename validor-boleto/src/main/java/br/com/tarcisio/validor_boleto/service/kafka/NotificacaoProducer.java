package br.com.tarcisio.validor_boleto.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.tarcisio.api_boleto.avro.Boleto;

@Service
public class NotificacaoProducer {
    
    @Value("${spring.kafka.topico-notificacao}")
    public String topico;

    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public NotificacaoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(Boleto boleto) {
        this.kafkaTemplate.send(topico, boleto);
    }
}
