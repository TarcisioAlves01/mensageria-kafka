package br.com.tarcisio.validor_boleto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ValidorBoletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidorBoletoApplication.class, args);
	}

}
