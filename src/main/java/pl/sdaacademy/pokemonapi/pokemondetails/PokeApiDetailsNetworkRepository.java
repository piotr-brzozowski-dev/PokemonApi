package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class PokeApiDetailsNetworkRepository {

    private final RestTemplate restTemplate;
    private final String url;

    public PokeApiDetailsNetworkRepository(
            RestTemplate restTemplate,
            @Value("${pokeapi.details.url}") String url
    ) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    PokeApiDetailsResult fetchPokemonDetailsResult(String pokemonName) {
        String fullUrl = String.format(url, pokemonName);
        return restTemplate.getForObject(fullUrl, PokeApiDetailsResult.class);
    }
}
