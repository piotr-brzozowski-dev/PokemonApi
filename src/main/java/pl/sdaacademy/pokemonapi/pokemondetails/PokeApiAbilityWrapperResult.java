package pl.sdaacademy.pokemonapi.pokemondetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokeApiAbilityWrapperResult {
    private PokeApiAbilityResult ability;
    @JsonProperty("is_hidden")
    private boolean isHidden;
    private int slot;

    public PokeApiAbilityWrapperResult(PokeApiAbilityResult ability, boolean isHidden, int slot) {
        this.ability = ability;
        this.isHidden = isHidden;
        this.slot = slot;
    }

    public PokeApiAbilityWrapperResult() {
    }

    public PokeApiAbilityResult getAbility() {
        return ability;
    }

    public void setAbility(PokeApiAbilityResult ability) {
        this.ability = ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}

class PokeApiAbilityResult {
    private String name;
    private String url;

    public PokeApiAbilityResult() {
    }

    public PokeApiAbilityResult(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

