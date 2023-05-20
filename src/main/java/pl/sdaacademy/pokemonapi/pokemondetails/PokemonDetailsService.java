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

    private final PokemonDetailsRepository pokemonDetailsRepository;
    private final PokemonDetailsMapper pokemonDetailsMapper;

    PokemonDetailsService(
            PokemonListItemRepository pokemonListItemRepository,
            PokeApiDetailsNetworkRepository pokeApiDetailsNetworkRepository,
            PokemonDetailsRepository pokemonDetailsRepository,
            PokemonDetailsMapper pokemonDetailsMapper
    ) {
        this.pokeApiDetailsNetworkRepository = pokeApiDetailsNetworkRepository;
        this.pokemonListItemRepository = pokemonListItemRepository;
        this.pokemonDetailsRepository = pokemonDetailsRepository;
        this.pokemonDetailsMapper = pokemonDetailsMapper;
    }

    PokemonDetailsEntity getPokemonDetails(String pokemonName) {
        PokemonListItemEntity pokemonListItemEntity =
                pokemonListItemRepository.findByNameIgnoreCase(pokemonName)
                        .orElseThrow(() -> new NoPokemonFoundException(pokemonName));

        return pokemonDetailsRepository.findById(pokemonName)
                .orElseGet(() -> {
                    PokeApiDetailsResult pokeApiDetailsResult =
                            pokeApiDetailsNetworkRepository
                                    .fetchPokemonDetailsResult(pokemonListItemEntity.getId());
                    PokemonDetailsEntity entity = pokemonDetailsMapper.toEntity(pokeApiDetailsResult);
                    return pokemonDetailsRepository.save(entity);
                });
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
