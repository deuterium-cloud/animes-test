package cloud.deuterium.testing.anime.datafaker;

import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.repository.AnimeRepository;
import cloud.deuterium.testing.anime.service.AnimeServiceImpl;
import net.datafaker.FakeCollection;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 * https://github.com/datafaker-net/datafaker
 */

@ExtendWith(SpringExtension.class)
public class AnimeServiceWithDatafakerTest {

    private static Faker faker;

    @InjectMocks
    private AnimeServiceImpl animeService;

    @Mock
    private AnimeRepository animeRepository;

    @BeforeAll
    static void init() {
        faker = new Faker();
    }

    @Test
    void getAll(){

        List<Anime> animes = getAnimes();

        when(animeRepository.getAll()).thenReturn(animes);

        List<Anime> allAnimes = animeService.getAllAnimes();
        System.out.println(allAnimes);

        assertFalse(allAnimes.isEmpty());

    }

    private List<Anime> getAnimes() {

        List<String> names = new FakeCollection.Builder<String>()
                .suppliers(() -> faker.witcher().character(), () -> faker.lordOfTheRings().character())
                .minLen(3)
                .maxLen(6).build().get();

        return names.stream()
                .map(name -> new Anime(
                        UUID.randomUUID(),
                        name,
                        faker.random().nextInt(100),
                        faker.date().future(100, TimeUnit.DAYS).toInstant()
                ))
                .toList();
    }

}
