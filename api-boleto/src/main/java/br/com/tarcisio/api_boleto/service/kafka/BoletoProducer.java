package br.com.tarcisio.api_boleto.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.tarcisio.api_boleto.avro.Boleto;

@Component
public class BoletoProducer {

    @Value("${spring.kafka.topico-boleto}")
    public String topico;

    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public BoletoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(Boleto boleto) {
        this.kafkaTemplate.send(topico, criarChave(boleto), boleto);
    }

    private String criarChave(Boleto boleto) {
        if (boleto.getCodigoBarras().toString().substring(0, 1).equals("1")) {
            return "chave-01";
        }

        return "chave-02";
    }
}
