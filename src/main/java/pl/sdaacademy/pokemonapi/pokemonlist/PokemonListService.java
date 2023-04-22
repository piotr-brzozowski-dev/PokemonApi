package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class PokemonListService {

    private final PokeApiListNetworkRepository pokeApiListNetworkRepository;
    private final PokemonListItemRepository pokemonListItemRepository;

    private final PokemonListItemMapper pokemonListItemMapper;

    PokemonListService(
            PokeApiListNetworkRepository pokeApiListNetworkRepository,
            PokemonListItemRepository pokemonListItemRepository,
            PokemonListItemMapper pokemonListItemMapper
    ) {
        this.pokeApiListNetworkRepository = pokeApiListNetworkRepository;
        this.pokemonListItemRepository = pokemonListItemRepository;
        this.pokemonListItemMapper = pokemonListItemMapper;
    }

    List<PokemonListItemEntity> getPokemonItemList() {
        List<PokeApiListItemResult> results = new ArrayList<>();
        PokeApiListResult result;
        int limit = 100;
        int offset = 0;
        do {
            result = pokeApiListNetworkRepository.fetchPokemonListResult(limit, offset);
            results.addAll(result.getResults());
            offset += limit;
        } while (result.getNext() != null);
        List<PokemonListItemEntity> pokemonListItemEntities =
                results.stream().map(pokemonListItemMapper::toEntity).toList();
        return pokemonListItemRepository.saveAll(pokemonListItemEntities);
    }
}
