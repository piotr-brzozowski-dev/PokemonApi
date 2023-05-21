package pl.sdaacademy.pokemonapi.healtcheck.pokemonlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

@Component("pokeapilist")
public class PokeApiListHealthCheck implements HealthIndicator, HealthContributor {
    private final String url;

    @Autowired
    public PokeApiListHealthCheck(@Value("${pokeapi.url}") String url) {
        this.url = url;
    }

    @Override
    public Health health() {
        try(Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(
                    new URL(String.format(url, 0, 1)).getHost()
                    , 80));
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.toString())
                    .build();
        }
        return Health.up().build();
    }
}
