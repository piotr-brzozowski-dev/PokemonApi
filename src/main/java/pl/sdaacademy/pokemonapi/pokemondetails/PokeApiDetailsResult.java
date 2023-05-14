package pl.sdaacademy.pokemonapi.pokemondetails;

import java.util.List;

public class PokeApiDetailsResult {
    private List<PokeApiAbilityWrapperResult> abilities;
    private int height;
    private int weight;

    private List<PokeApiTypeWrapperResult> types;

    private PokeApiSpritesResult sprites;

    public List<PokeApiAbilityWrapperResult> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokeApiAbilityWrapperResult> abilities) {
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

    public List<PokeApiTypeWrapperResult> getTypes() {
        return types;
    }

    public void setTypes(List<PokeApiTypeWrapperResult> types) {
        this.types = types;
    }

    public PokeApiSpritesResult getSprites() {
        return sprites;
    }

    public void setSprites(PokeApiSpritesResult sprites) {
        this.sprites = sprites;
    }
}

