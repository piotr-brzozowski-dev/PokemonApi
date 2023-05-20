package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.stereotype.Service;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemEntity;
import pl.sdaacademy.pokemonapi.pokemonlist.PokemonListItemRepository;

import java.util.List;
import java.util.Objects;

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

    PokemonDetailsEntity getPokemonDetails(String pokemonName) {
        PokemonListItemEntity pokemonListItemEntity =
                pokemonListItemRepository.findByNameIgnoreCase(pokemonName)
                        .orElseThrow(() -> new NoPokemonFoundException(pokemonName));
        PokeApiDetailsResult pokeApiDetailsResult =
                pokeApiDetailsNetworkRepository
                        .fetchPokemonDetailsResult(pokemonListItemEntity.getId());
        return pokemonDetailsMapper.toEntity(pokeApiDetailsResult);
    }

    List<PokemonDetailsEntity> getPokemonDetails(List<String> pokemonNames) {
        return pokemonNames.stream().map(pokemonName -> {
                    try {
                        return getPokemonDetails(pokemonName);
                    } catch (NoPokemonFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
