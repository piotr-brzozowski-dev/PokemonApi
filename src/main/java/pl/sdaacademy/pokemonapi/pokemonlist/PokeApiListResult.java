package pl.sdaacademy.pokemonapi.pokemonlist;

import java.util.List;

public class PokeApiListResult {
    private Integer count;
    private String next;
    private String previous;

    private List<PokeApiListItemResult> results;

    public PokeApiListResult() {
    }

    public PokeApiListResult(Integer count, String next, String previous, List<PokeApiListItemResult> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokeApiListItemResult> getResults() {
        return results;
    }

    public void setResults(List<PokeApiListItemResult> results) {
        this.results = results;
    }
}

class PokeApiListItemResult {
    private String name;
    private String url;

    public PokeApiListItemResult() {
    }

    public PokeApiListItemResult(String name, String url) {
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
