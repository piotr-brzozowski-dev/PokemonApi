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

    @Test
    void given_not_empty_pokemon_db_and_difference_between_db_and_network_when_fetch_the_list_then_db_should_be_cleared_and_fetch_should_happen() {
        //given
        FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokeApiListNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokeApiListNetworkRepository.class);

        PokemonListLoaderService pokemonListLoaderService = new PokemonListLoaderService(
                pokeApiListNetworkRepository,
                pokemonListItemRepository,
                fetchPokemonListUseCase
        );
        PokeApiListResult pokeApiListResult = new PokeApiListResult();
        pokeApiListResult.setCount(20);
        Mockito.when(pokemonListItemRepository.count()).thenReturn(10L);
        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(1, 0)).thenReturn(pokeApiListResult);

        //when
        pokemonListLoaderService.loadPokemonList();

        //then
        Mockito.verify(pokemonListItemRepository).deleteAll();
        Mockito.verify(fetchPokemonListUseCase).execute();
    }

    @Test
    void given_not_empty_pokemon_db_and_no_difference_between_db_and_network_when_fetch_the_list_then_nothing_should_happen() {
        //given
        FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokeApiListNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokeApiListNetworkRepository.class);

        PokemonListLoaderService pokemonListLoaderService = new PokemonListLoaderService(
                pokeApiListNetworkRepository,
                pokemonListItemRepository,
                fetchPokemonListUseCase
        );
        PokeApiListResult pokeApiListResult = new PokeApiListResult();
        pokeApiListResult.setCount(10);
        Mockito.when(pokemonListItemRepository.count()).thenReturn(10L);
        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(1, 0)).thenReturn(pokeApiListResult);

        //when
        pokemonListLoaderService.loadPokemonList();

        //then
        Mockito.verify(fetchPokemonListUseCase, Mockito.never()).execute();
    }
}