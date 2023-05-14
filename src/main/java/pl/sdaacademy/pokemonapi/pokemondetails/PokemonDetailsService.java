package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemEntity;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemRepository;

@Service
class PokemonDetailsService {

    private final PokemonListItemRepository pokemonListItemRepository;
    private final String url;

    PokemonDetailsService(
            PokemonListItemRepository pokemonListItemRepository,
            @Value("${pokeapi.details.url}") String url
    ) {
        this.url = url;
        this.pokemonListItemRepository = pokemonListItemRepository;
    }

    String getPokemonDetailsUrl(String pokemonName) {
        PokemonListItemEntity pokemonListItemEntity =
                pokemonListItemRepository.findByName(pokemonName)
                        .orElseThrow(() -> new NoPokemonFoundException(pokemonName));
        return String.format(url, pokemonListItemEntity.getId());
    }

}
