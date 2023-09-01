package br.com.ibmec.cloud.demoapi.demoapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
    
@Configuration
public class ApiDocumentationConfiguration { 

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("IBMEC - DISCIPLINA CLOUD")
                        .description("Exemplo de utilização do Spring Boot")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Rafael Cruz")
                                .email("teste@teste.com.br")));
    }
}
