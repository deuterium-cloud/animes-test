package cloud.deuterium.testing.anime.podam;

import cloud.deuterium.testing.anime.controller.AnimeController;
import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.service.AnimeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@ExtendWith(SpringExtension.class)
public class AnimeControllerWithPodamTest {

    private static PodamFactory factory;
    private static ObjectMapper objectMapper;

    @Mock
    private AnimeServiceImpl animeService;

    private MockMvc mockMvc;

    @BeforeAll
    static void init() {
        factory = new PodamFactoryImpl();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AnimeController(animeService))
                .build();
    }

    @Test
    @DisplayName("Should return List of Animes")
    void getAll() throws Exception {

        ArrayList<Anime> animes = factory.manufacturePojo(ArrayList.class, Anime.class);

        when(animeService.getAllAnimes()).thenReturn(animes);

        this.mockMvc.perform(get("/api/v1/animes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").isNotEmpty());
    }

    @Test
    @DisplayName("Should create new Anime and return status code 200")
    void create() throws Exception {
        Anime anime = factory.manufacturePojo(Anime.class);

        when(animeService.saveAnime(any(Anime.class))).thenReturn(anime);

        this.mockMvc.perform(post("/api/v1/animes")
                        .content(objectMapper.writeValueAsString(anime))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

}
