package pl.sdaacademy.pokemonapi.pokemondetails;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemEntity;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDetailsServiceTest {

    @Test
    void given_pokemon_details_not_stored_in_db_when_get_details_then_network_call_should_be_proceed() {
        //given
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository = Mockito.mock(PokeApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(
                pokemonListItemRepository,
                pokeApiDetailsNetworkRepository,
                pokemonDetailsRepository,
                pokemonDetailsMapper
        );
        String pokemonName = "Bulbasaur";
        PokemonDetailsEntity pokemonDetailsEntity = Mockito.mock(PokemonDetailsEntity.class);
        PokemonListItemEntity pokemonListItemEntity = new PokemonListItemEntity(1L, pokemonName);
        Mockito.when(pokemonListItemRepository.findByNameIgnoreCase(pokemonName)).thenReturn(Optional.of(pokemonListItemEntity));
        Mockito.when(pokemonDetailsMapper.toEntity(Mockito.any())).thenReturn(pokemonDetailsEntity);

        //when
        pokemonDetailsService.getPokemonDetails(pokemonName);

        //then
        Mockito.verify(pokeApiDetailsNetworkRepository).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository).save(pokemonDetailsEntity);
    }

    @Test
    void given_pokemon_details_stored_in_db_when_get_details_then_details_should_be_provided_from_db() {
        //given
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository = Mockito.mock(PokeApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(
                pokemonListItemRepository,
                pokeApiDetailsNetworkRepository,
                pokemonDetailsRepository,
                pokemonDetailsMapper
        );
        String pokemonName = "Bulbasaur";
        PokemonDetailsEntity pokemonDetailsEntity = Mockito.mock(PokemonDetailsEntity.class);
        PokemonListItemEntity pokemonListItemEntity = new PokemonListItemEntity(1L, pokemonName);
        Mockito.when(pokemonDetailsRepository.findById(pokemonName)).thenReturn(Optional.of(pokemonDetailsEntity));
        Mockito.when(pokemonListItemRepository.findByNameIgnoreCase(pokemonName)).thenReturn(Optional.of(pokemonListItemEntity));

        //when
        pokemonDetailsService.getPokemonDetails(pokemonName);

        //then
        Mockito.verify(pokeApiDetailsNetworkRepository, Mockito.never()).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository, Mockito.never()).save(pokemonDetailsEntity);
    }

    @Test
    void given_pokemon_not_exist_in_db_when_get_details_then_details_not_be_provided() {
        //given
        PokemonListItemRepository pokemonListItemRepository = Mockito.mock(PokemonListItemRepository.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository = Mockito.mock(PokeApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(
                pokemonListItemRepository,
                pokeApiDetailsNetworkRepository,
                pokemonDetailsRepository,
                pokemonDetailsMapper
        );
        String pokemonName = "Bulbasaur";
        Mockito.when(pokemonDetailsRepository.findById(pokemonName)).thenReturn(Optional.empty());

        //when
        //then

        assertThrows(NoPokemonFoundException.class, () -> {
            pokemonDetailsService.getPokemonDetails(pokemonName);
        });

        //then
        Mockito.verify(pokemonDetailsRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(pokeApiDetailsNetworkRepository, Mockito.never()).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository, Mockito.never()).save(Mockito.any());
    }

}