package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PokemonDetailsMapper {

    PokemonDetailsEntity toEntity(PokeApiDetailsResult pokeApiDetailsResult) {
        String image = pokeApiDetailsResult.getSprites().getOther().getOfficialArtwork().getFrontDefault();
        List<String> abilities = pokeApiDetailsResult
                .getAbilities()
                .stream()
                .map(item -> item.getAbility().getName())
                .toList();
        List<String> types = pokeApiDetailsResult
                .getTypes()
                .stream()
                .map(item -> item.getType().getName())
                .toList();
        return new PokemonDetailsEntity(
                pokeApiDetailsResult.getHeight(),
                pokeApiDetailsResult.getWeight(),
                image,
                types,
                abilities
        );
    }
}
