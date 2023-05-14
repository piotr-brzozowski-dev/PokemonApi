package pl.sdaacademy.pokemonapi.pokemondetails;

public class PokeApiTypeWrapperResult {
    private int slot;
    private PokeApiTypeResult type;

    public PokeApiTypeWrapperResult() {
    }

    public PokeApiTypeWrapperResult(int slot, PokeApiTypeResult type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public PokeApiTypeResult getType() {
        return type;
    }

    public void setType(PokeApiTypeResult type) {
        this.type = type;
    }
}

class PokeApiTypeResult {
    private String name;
    private String url;

    public PokeApiTypeResult(String name, String url) {
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
