package pl.sdaacademy.pokemonapi.pokemondetails;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PokemonDetailsMapperTest {

    @Test
    void given_empty_pokemon_details_from_network_repository_when_map_to_entity_then_pokemon_details_entity_should_be_returned() {
        //given
        PokemonDetailsMapper pokemonDetailsMapper = new PokemonDetailsMapper();
        PokeApiDetailsResult pokeApiDetailsResult = new PokeApiDetailsResult(
                null,
                "test",
                200,
                20,
                null,
                null
        );

        PokemonDetailsEntity result = pokemonDetailsMapper.toEntity(pokeApiDetailsResult);

        assertEquals(200, result.getHeight());
        assertEquals(20, result.getWeight());
        assertNull(result.getImage());
        assertNull(result.getAbilities());
        assertNull(result.getTypes());
    }

    @Test
    void given_pokemon_details_from_network_repository_when_map_to_entity_then_pokemon_details_entity_should_be_returned() {
        //given
        PokemonDetailsMapper pokemonDetailsMapper = new PokemonDetailsMapper();
        PokeApiSpritesResult sprites = new PokeApiSpritesResult(
                new PokeApiOtherResult(
                        new PokeApiOfficialArtworkResult("image1", "image2")
                )
        );
        List<PokeApiAbilityWrapperResult> abilities = Arrays.asList(
                new PokeApiAbilityWrapperResult(
                        new PokeApiAbilityResult("test", "testurl"),
                        false,
                        1
                ),
                new PokeApiAbilityWrapperResult(
                        new PokeApiAbilityResult("test1", "test1url"),
                        true,
                        2
                )
        );
        List<PokeApiTypeWrapperResult> types = Arrays.asList(
                new PokeApiTypeWrapperResult(
                        1,
                        new PokeApiTypeResult("testtype", "testtypeurl")
                ),
                new PokeApiTypeWrapperResult(
                        2,
                        new PokeApiTypeResult("testtype1", "testtypeurl1")
                )
        );
        PokeApiDetailsResult pokeApiDetailsResult = new PokeApiDetailsResult(
                abilities,
                "test",
                200,
                20,
                types,
                sprites
        );

        PokemonDetailsEntity result = pokemonDetailsMapper.toEntity(pokeApiDetailsResult);

        assertEquals(200, result.getHeight());
        assertEquals(20, result.getWeight());
        assertEquals("test", result.getName());
        assertEquals("image1", result.getImage());
        assertEquals(Arrays.asList("test", "test1"), result.getAbilities());
        assertEquals(Arrays.asList("testtype", "testtype1"), result.getTypes());
    }
}