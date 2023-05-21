package pl.sdaacademy.pokemonapi.healtcheck.pokemonlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("pokemonlistdb")
public class PokemonListDatabaseHealthCheck implements HealthIndicator, HealthContributor {
    private final DataSource source;

    @Autowired
    public PokemonListDatabaseHealthCheck(DataSource source) {
        this.source = source;
    }

    @Override
    public Health health() {
        try (Connection connection = source.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT COUNT(*) FROM pokemon_list_item_entity");
        } catch (SQLException e) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
