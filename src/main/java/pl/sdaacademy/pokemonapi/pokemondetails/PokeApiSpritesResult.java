package pl.sdaacademy.pokemonapi.pokemondetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokeApiSpritesResult {
    private PokeApiOtherResult other;

    public PokeApiOtherResult getOther() {
        return other;
    }

    public void setOther(PokeApiOtherResult other) {
        this.other = other;
    }
}

class PokeApiOtherResult {
    @JsonProperty("official-artwork")
    private PokeApiOfficialArtworkResult officialArtwork;

    public PokeApiOfficialArtworkResult getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(PokeApiOfficialArtworkResult officialArtwork) {
        this.officialArtwork = officialArtwork;
    }
}

class PokeApiOfficialArtworkResult {
    @JsonProperty("front_default")
    private String frontDefault;
    @JsonProperty("front_shiny")
    private String frontShiny;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }
}