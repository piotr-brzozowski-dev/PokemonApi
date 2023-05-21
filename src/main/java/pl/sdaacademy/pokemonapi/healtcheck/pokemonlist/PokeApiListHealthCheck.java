package pl.sdaacademy.pokemonapi.healtcheck.pokemonlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sdaacademy.pokemonapi.pokemonlist.PokeApiListResult;

@Component("pokeapilist")
public class PokeApiListHealthCheck implements HealthIndicator, HealthContributor {

    private final RestTemplate restTemplate;
    private final String url;

    @Autowired
    public PokeApiListHealthCheck(RestTemplate restTemplate,
                                  @Value("${pokeapi.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Health health() {
        try {
            restTemplate.getForObject(String.format(url, 0, 1), PokeApiListResult.class);
        } catch (HttpServerErrorException e) {
            Health.down().build();
        }
        return Health.up().build();
    }
}
