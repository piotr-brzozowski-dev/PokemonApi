package pl.sdaacademy.pokemonapi.pokemonlist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PokemonListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String pokemonDetailsUrl;

    public PokemonListItemEntity() {
    }

    public PokemonListItemEntity(String name, String pokemonDetailsUrl) {
        this.id = id;
        this.name = name;
        this.pokemonDetailsUrl = pokemonDetailsUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPokemonDetailsUrl() {
        return pokemonDetailsUrl;
    }

    public void setPokemonDetailsUrl(String pokemonDetailsUrl) {
        this.pokemonDetailsUrl = pokemonDetailsUrl;
    }
}
