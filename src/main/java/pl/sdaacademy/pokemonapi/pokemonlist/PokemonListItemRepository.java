package pl.sdaacademy.pokemonapi.pokemonlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonListItemRepository extends JpaRepository<PokemonListItemEntity, Long> {

    Optional<PokemonListItemEntity> findByName(String name);
}
