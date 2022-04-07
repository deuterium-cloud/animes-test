package cloud.deuterium.testing.anime.datafaker;

import cloud.deuterium.testing.anime.controller.AnimeController;
import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.service.AnimeServiceImpl;
import net.datafaker.Faker;
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

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@ExtendWith(SpringExtension.class)
public class AnimeControllerWithDatafakerTest {

    private static Faker faker;

    @Mock
    private AnimeServiceImpl animeService;

    private MockMvc mockMvc;

    @BeforeAll
    static void init() {
        faker = new Faker();
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

        when(animeService.getAllAnimes()).thenReturn(getAnimes());

        this.mockMvc.perform(get("/api/v1/animes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].timestamp").value(1641031523.653000000));

    }

    @Test
    @DisplayName("Should create new Anime and return status code 200")
    void create() throws Exception {

        String json = getAnimeJson();
        Anime anime = getAnime();

        when(animeService.saveAnime(any(Anime.class))).thenReturn(anime);

        this.mockMvc.perform(post("/api/v1/animes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value("589c6950-0830-4afb-a769-8e2f7169de69"))
                .andExpect(jsonPath("$.name").value("Berserk"))
                .andDo(print());
    }

    private String getAnimeJson() {
        return """
                {
                    "name": "Berserk",
                    "episodes": 45,
                    "timestamp": "2022-04-07T14:35:23.102311229Z"
                }
                """;
    }

    private Anime getAnime() {
        return new Anime(
                UUID.fromString("589c6950-0830-4afb-a769-8e2f7169de69"),
                "Berserk",
                45,
                Instant.parse("2022-04-07T14:35:23.102311229Z")
        );
    }

    private List<Anime> getAnimes() {

        Clock clock = Clock.fixed(Instant.parse("2022-01-01T10:05:23.653Z"), ZoneId.of("UTC"));

        return Stream.of(24, 10, 30, 45, 120, 80)
                .map(i -> new Anime(
                        UUID.randomUUID(),
                        faker.lordOfTheRings().character(),
                        i,
                        Instant.now(clock)
                ))
                .toList();
    }
}
