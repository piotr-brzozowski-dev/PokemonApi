package pl.sdaacademy.pokemonapi.pokemondetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon/details")
class PokemonDetailsController {

    private final PokemonDetailsService pokemonDetailsService;

    PokemonDetailsController(PokemonDetailsService pokemonDetailsService) {
        this.pokemonDetailsService = pokemonDetailsService;
    }

    @GetMapping("/{pokemonName}")
    PokemonDetailsEntity getPokemonDetails(@PathVariable String pokemonName) {
        return pokemonDetailsService.getPokemonDetailsUrl(pokemonName);
    }

    @ExceptionHandler(NoPokemonFoundException.class)
    ResponseEntity<Object> mapNoPokemonFoundException(NoPokemonFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
