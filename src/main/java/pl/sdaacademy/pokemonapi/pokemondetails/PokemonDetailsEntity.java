package pl.sdaacademy.pokemonapi.pokemondetails;

import java.util.List;

public class PokemonDetailsEntity {
    private int height;
    private int weight;
    private String image;

    private List<String> types;
    private List<String> abilities;

    public PokemonDetailsEntity(int height, int weight, String image, List<String> types, List<String> abilities) {
        this.height = height;
        this.weight = weight;
        this.image = image;
        this.types = types;
        this.abilities = abilities;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }
}
