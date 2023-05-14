package pl.sdaacademy.pokemonapi.pokemonlist;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PokemonListController.class)
class PokemonListControllerTest {

    @MockBean
    private PokemonListService pokemonListService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void given_pokemons_existing_in_db_when_send_GET_pokemon_list_request_then_pokemon_list_should_be_returned() throws Exception {
        //given
        List<PokemonListItemEntity> pokemonListItemEntities = Arrays.asList(
                new PokemonListItemEntity(1L, "Pikachu"),
                new PokemonListItemEntity(2L, "Bulbasaur")
        );
        Mockito.when(pokemonListService.getPokemonItemList()).thenReturn(pokemonListItemEntities);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/list")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Pikachu")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Bulbasaur")));

    }

    @Test
    void given_pokemons_not_exist_in_db_when_send_GET_pokemon_list_request_then_empty_list_should_be_returned() throws Exception {
        //given
        Mockito.when(pokemonListService.getPokemonItemList()).thenReturn(Collections.emptyList());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/list")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    void given_internal_server_issue_when_send_GET_pokemon_list_request_then_500_status_should_be_returned() {
        //given
        Mockito.when(pokemonListService.getPokemonItemList()).thenThrow(RuntimeException.class);

        //when
        //then
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/list")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is5xxServerError());
        });
    }
}