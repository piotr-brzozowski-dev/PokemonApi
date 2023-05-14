package pl.sdaacademy.pokemonapi.pokemondetails;

public class NoPokemonFoundException extends RuntimeException {

    NoPokemonFoundException(String pokemonName) {
        super(String.format("Can't find %s", pokemonName));
    }
}
