package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon/details")
class PokemonDetailsController {

    private final PokemonDetailsService pokemonDetailsService;

    PokemonDetailsController(PokemonDetailsService pokemonDetailsService) {
        this.pokemonDetailsService = pokemonDetailsService;
    }

    @GetMapping("/{pokemonName}")
    PokemonDetailsEntity getPokemonDetails(@PathVariable String pokemonName) {
        return pokemonDetailsService.getPokemonDetails(pokemonName);
    }

    @GetMapping
    List<PokemonDetailsEntity> getPokemonDetails(@RequestParam List<String> pokemonNames) {
        return pokemonDetailsService.getPokemonDetails(pokemonNames);
    }

    @ExceptionHandler(NoPokemonFoundException.class)
    ResponseEntity<Object> mapNoPokemonFoundException(NoPokemonFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
