package pl.sdaacademy.pokemonapi.pokemondetails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sdaacademy.pokemonapi.PokemonApiApplication;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PokemonApiApplication.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class PokemonDetailsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void given_name_of_existing_pokemon_when_GET_pokemon_details_then_pokemon_details_should_be_returned() throws Exception {
        //given
        String pokemonName = "bulbasaur";

        //when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pokemon/details/" + pokemonName)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.height", equalTo(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", equalTo(69)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image", equalTo("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.types[0]", equalTo("grass")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.types[1]", equalTo("poison")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.abilities[0]", equalTo("overgrow")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.abilities[1]", equalTo("chlorophyll")));
    }

    @Test
    void given_name_of_not_existing_pokemon_when_GET_pokemon_details_then_404_not_found_should_be_returned() throws Exception {
        //given
        String pokemonName = "bulbasa";


        //when
        //then
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pokemon/details/" + pokemonName)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$", equalTo(String.format("Can't find %s", pokemonName))
                        )
                );
    }
}