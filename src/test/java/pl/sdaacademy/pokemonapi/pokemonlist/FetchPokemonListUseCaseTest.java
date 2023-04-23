package pl.sdaacademy.pokemonapi.pokemonlist;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FetchPokemonListUseCaseTest {

    @Test
    void given_network_api_when_fetching_pokemon_list_then_pokemon_list_should_should_be_mapped_to_entities_and_saved_in_repo() {
        //given
        PokeApiListNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokeApiListNetworkRepository.class);
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokemonListItemMapper pokemonListItemMapper = Mockito.mock(PokemonListItemMapper.class);
        FetchPokemonListUseCase fetchPokemonListUseCase = new FetchPokemonListUseCase(
                pokeApiListNetworkRepository,
                pokemonListItemRepository,
                pokemonListItemMapper
        );
        PokeApiListItemResult pokeApiListItemResult = Mockito.mock(PokeApiListItemResult.class);
        PokeApiListResult pokeApiListResult = Mockito.mock(PokeApiListResult.class);
        PokemonListItemEntity pokemonListItemEntity = Mockito.mock(PokemonListItemEntity.class);
        Mockito.when(pokeApiListResult.getNext()).thenReturn(null);
        Mockito.when(pokeApiListResult.getResults()).thenReturn(Collections.singletonList(pokeApiListItemResult));
        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(100, 0)).thenReturn(pokeApiListResult);
        Mockito.when(pokemonListItemMapper.toEntity(pokeApiListItemResult)).thenReturn(pokemonListItemEntity);
        Mockito.when(pokemonListItemRepository.saveAll(Collections.singletonList(pokemonListItemEntity))).thenReturn(
                Collections.singletonList(pokemonListItemEntity));

        //when
        List<PokemonListItemEntity> result = fetchPokemonListUseCase.execute();

        //then
        assertEquals(Collections.singletonList(pokemonListItemEntity), result);
    }

    @Test
    void given_network_api_when_fetching_empty_pokemon_list_then_pokemon_list_should_should_be_mapped_to_entities_and_saved_in_repo() {
        //given
        PokeApiListNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokeApiListNetworkRepository.class);
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokemonListItemMapper pokemonListItemMapper = Mockito.mock(PokemonListItemMapper.class);
        FetchPokemonListUseCase fetchPokemonListUseCase = new FetchPokemonListUseCase(
                pokeApiListNetworkRepository,
                pokemonListItemRepository,
                pokemonListItemMapper
        );
        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(100, 0)).thenReturn(new PokeApiListResult());
        Mockito.when(pokemonListItemRepository.saveAll(Collections.emptyList())).thenReturn(Collections.emptyList());

        //when
        List<PokemonListItemEntity> result = fetchPokemonListUseCase.execute();

        //then
        assertTrue(result.isEmpty());
    }
}