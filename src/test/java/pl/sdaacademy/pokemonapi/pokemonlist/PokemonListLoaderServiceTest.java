package pl.sdaacademy.pokemonapi.pokemonlist;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PokemonListLoaderServiceTest {

    @Test
    void given_no_pokemon_db_when_fetch_the_list_then_results_from_network_should_be_fetched() {
        //given
        FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokeApiListNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokeApiListNetworkRepository.class);

        PokemonListLoaderService pokemonListLoaderService = new PokemonListLoaderService(
                pokeApiListNetworkRepository,
                pokemonListItemRepository,
                fetchPokemonListUseCase
        );
        Mockito.when(pokemonListItemRepository.count()).thenReturn(0L);

        //when
        pokemonListLoaderService.loadPokemonList();

        //then
        Mockito.verify(fetchPokemonListUseCase, Mockito.times(1)).execute();
    }
}