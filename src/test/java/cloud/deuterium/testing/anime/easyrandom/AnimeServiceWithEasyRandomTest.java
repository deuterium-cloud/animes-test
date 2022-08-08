package cloud.deuterium.testing.anime.easyrandom;

import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.repository.AnimeRepository;
import cloud.deuterium.testing.anime.service.AnimeServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by Milan Stojkovic 08-Aug-2022
 *
 */

@ExtendWith(SpringExtension.class)
public class AnimeServiceWithEasyRandomTest {

    private static EasyRandom generator;

    @InjectMocks
    private AnimeServiceImpl animeService;

    @Mock
    private AnimeRepository animeRepository;

    @BeforeAll
    static void init() {
        generator = new EasyRandom();
    }

    @Test
    void getAll() {

        List<Anime> animes = generator.objects(Anime.class, 10).toList();

        when(animeRepository.getAll()).thenReturn(animes);

        List<Anime> allAnimes = animeService.getAllAnimes();

        assertFalse(allAnimes.isEmpty());
        assertEquals(10, allAnimes.size());

    }
}
