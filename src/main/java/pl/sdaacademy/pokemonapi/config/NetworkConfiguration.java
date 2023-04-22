package pl.sdaacademy.pokemonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NetworkConfiguration {

    @Bean
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }
}
