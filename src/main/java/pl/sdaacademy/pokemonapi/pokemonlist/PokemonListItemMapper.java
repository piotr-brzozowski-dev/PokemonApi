package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.stereotype.Component;

@Component
class PokemonListItemMapper {

    PokemonListItemEntity toEntity(PokeApiListItemResult pokeApiListItemResult) {
        return new PokemonListItemEntity(
                pokeApiListItemResult.getName(),
                pokeApiListItemResult.getUrl()
        );
    }
}
