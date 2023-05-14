package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemEntity;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemRepository;

@Service
class PokemonDetailsService {

    private final PokemonListItemRepository pokemonListItemRepository;
    private final PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository;
    private final PokemonDetailsMapper pokemonDetailsMapper;

    PokemonDetailsService(
            PokemonListItemRepository pokemonListItemRepository,
            PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository,
            PokemonDetailsMapper pokemonDetailsMapper
    ) {
        this.pokeApiDetailsNetworkRepository = pokeApiDetailsNetworkRepository;
        this.pokemonListItemRepository = pokemonListItemRepository;
        this.pokemonDetailsMapper = pokemonDetailsMapper;
    }

    PokemonDetailsEntity getPokemonDetailsUrl(String pokemonName) {
        PokemonListItemEntity pokemonListItemEntity =
                pokemonListItemRepository.findByName(pokemonName)
                        .orElseThrow(() -> new NoPokemonFoundException(pokemonName));
        PokeApiDetailsResult pokeApiDetailsResult =
                pokeApiDetailsNetworkRepository
                        .fetchPokemonDetailsResult(pokemonListItemEntity.getId());
        return pokemonDetailsMapper.toEntity(pokeApiDetailsResult);
    }

}
