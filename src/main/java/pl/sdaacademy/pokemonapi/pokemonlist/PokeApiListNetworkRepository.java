package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class PokeApiListNetworkRepository {

    private final String url;
    private final RestTemplate restTemplate;

    PokeApiListNetworkRepository(@Value("${pokeapi.url}") String url, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    PokeApiListResult fetchPokemonListResult(int limit, int offeset) {
        String fullUrl = String.format(url, limit, offeset);
        PokeApiListResult result = restTemplate.getForObject(fullUrl, PokeApiListResult.class);
        return result;
    }
}
