package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PokemonDetailsMapper {

    PokemonDetailsEntity toEntity(PokeApiDetailsResult pokeApiDetailsResult) {
        String image = null;
        if (pokeApiDetailsResult.getSprites() != null &&
                pokeApiDetailsResult.getSprites().getOther() != null
                && pokeApiDetailsResult.getSprites().getOther().getOfficialArtwork() != null) {
            image = pokeApiDetailsResult.getSprites().getOther().getOfficialArtwork().getFrontDefault();
        }
        List<String> abilities = null;
        if (pokeApiDetailsResult.getAbilities() != null) {
            abilities = pokeApiDetailsResult
                    .getAbilities()
                    .stream()
                    .map(item -> item.getAbility().getName())
                    .toList();
        }
        List<String> types = null;
        if (pokeApiDetailsResult.getTypes() != null) {
            types = pokeApiDetailsResult
                    .getTypes()
                    .stream()
                    .map(item -> item.getType().getName())
                    .toList();
        }
        return new PokemonDetailsEntity(
                pokeApiDetailsResult.getName(),
                pokeApiDetailsResult.getHeight(),
                pokeApiDetailsResult.getWeight(),
                image,
                types,
                abilities
        );
    }
}
