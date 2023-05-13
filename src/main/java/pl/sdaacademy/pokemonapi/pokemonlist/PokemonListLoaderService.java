package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class PokemonListLoaderService {

    private final PokeApiListNetworkRepository pokeApiListNetworkRepository;
    private final PokemonListItemRepository pokemonListItemRepository;

    private final FetchPokemonListUseCase fetchPokemonListUseCase;

    public PokemonListLoaderService(PokeApiListNetworkRepository pokeApiListNetworkRepository,
                                    PokemonListItemRepository pokemonListItemRepository,
                                    FetchPokemonListUseCase fetchPokemonListUseCase) {
        this.pokeApiListNetworkRepository = pokeApiListNetworkRepository;
        this.pokemonListItemRepository = pokemonListItemRepository;
        this.fetchPokemonListUseCase = fetchPokemonListUseCase;
    }

    @PostConstruct
    void loadPokemonList() {
        long repoCount = pokemonListItemRepository.count();
        if (repoCount == 0) {
            fetchPokemonListUseCase.execute();
        } else {
            PokeApiListResult countResult = pokeApiListNetworkRepository.fetchPokemonListResult(1, 0);
            int apiResponseCount = countResult.getCount();
            if (repoCount != apiResponseCount) {
                pokemonListItemRepository.deleteAll();
                fetchPokemonListUseCase.execute();
            }
        }
    }

    /*
    - jeśli nie ma elementów w repo to pobierz z use case
    - jeśli elementy są w repo, pobierz informację o ilości elementów w repo sieciowy: \
    jeśli liczby są różne, wyczyść bazę i pobierz z use case
    - jeśli elementy są w repo, pobierz informację o ilości elementów w repo sieciowy: \
    jeśli liczby są takie same, nie wykonuj żadnej operacji
     */
}
