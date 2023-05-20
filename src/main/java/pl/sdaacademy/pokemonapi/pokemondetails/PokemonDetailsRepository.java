package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.data.jpa.repository.JpaRepository;

interface PokemonDetailsRepository extends JpaRepository<PokemonDetailsEntity, String> {
}
