package br.com.tarcisio.api_boleto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        var info = new Info();
        info.title("API Bolero").description("API para pagamento de boletos")
                .version("v1");

        info.contact(new Contact().name("Tarcísio Alves")
                .email("tarcisio.alves@gmail.com"));

        info.license(new License().name("Apache 2.0")
                .url("http://voll.med/api/licenca"));

        return new OpenAPI().info(info);
    }

}
