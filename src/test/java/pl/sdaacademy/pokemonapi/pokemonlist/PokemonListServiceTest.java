package pl.sdaacademy.pokemonapi.pokemonlist;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonListServiceTest {

    @Test
    void given_non_empty_repository_when_requesting_pokemon_list_then_fetching_from_network_should_be_omitted() {
        //given
        FetchPokemonListUseCase useCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonListItemRepository repository = Mockito.mock(PokemonListItemRepository.class);
        PokemonListService pokemonListService = new PokemonListService(useCase, repository);
        Mockito.when(repository.count()).thenReturn(1L);
        List<PokemonListItemEntity> expectedResult = List.of(new PokemonListItemEntity(1L, "test"));
        Mockito.when(repository.findAll()).thenReturn(expectedResult);

        //when
        List<PokemonListItemEntity> results = pokemonListService.getPokemonItemList();

        //then
        assertEquals(expectedResult, results);
        Mockito.verify(useCase, Mockito.never()).execute();
    }

    @Test
    void given_empty_repository_when_requesting_pokemon_list_then_fetching_from_repository_should_be_omitted() {
        //given
        FetchPokemonListUseCase useCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonListItemRepository repository = Mockito.mock(PokemonListItemRepository.class);
        PokemonListService pokemonListService = new PokemonListService(useCase, repository);
        List<PokemonListItemEntity> expectedResult = List.of(new PokemonListItemEntity(1L, "test"));
        Mockito.when(repository.count()).thenReturn(0L);
        Mockito.when(useCase.execute()).thenReturn(expectedResult);

        //when
        List<PokemonListItemEntity> results = pokemonListService.getPokemonItemList();

        //then
        assertEquals(expectedResult, results);
        Mockito.verify(repository, Mockito.never()).findAll();
    }
}