package pl.sdaacademy.pokemonapi.healtcheck.pokemondetails;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("pokemondetailsdb")
public class PokemonDetailsDatabaseHealthCheck implements HealthIndicator, HealthContributor {

    private final DataSource dataSource;

    public PokemonDetailsDatabaseHealthCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT COUNT(*) FROM pokemon_details_entity");
        } catch (SQLException e) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
