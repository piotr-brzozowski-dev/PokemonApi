package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class PokemonListService {

    private final PokeApiListNetworkRepository pokeApiListNetworkRepository;

    PokemonListService(PokeApiListNetworkRepository pokeApiListNetworkRepository) {
        this.pokeApiListNetworkRepository = pokeApiListNetworkRepository;
    }

    List<PokeApiListItemResult> getPokemonItemList() {
        List<PokeApiListItemResult> results = new ArrayList<>();
        PokeApiListResult result;
        int limit = 100;
        int offset = 0;
        do {
            result = pokeApiListNetworkRepository.fetchPokemonListResult(limit, offset);
            results.addAll(result.getResults());
            offset += limit;
        } while (result.getNext() != null);
        return results;
    }
}
