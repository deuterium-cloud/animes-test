package cloud.deuterium.testing.anime.podam;

import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.repository.AnimeRepository;
import cloud.deuterium.testing.anime.service.AnimeServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 * https://medium.com/geekculture/java-unit-tests-make-easy-random-values-with-podam-2b1de8a56958
 */

@ExtendWith(SpringExtension.class)
public class AnimeServiceWithPodamTest {

    private static PodamFactory factory;

    @InjectMocks
    private AnimeServiceImpl animeService;

    @Mock
    private AnimeRepository animeRepository;

    @BeforeAll
    static void init() {
        factory = new PodamFactoryImpl();
    }

    @Test
    void getAll(){

        ArrayList<Anime> animes = factory.manufacturePojo(ArrayList.class, Anime.class);

        when(animeRepository.getAll()).thenReturn(animes);

        List<Anime> allAnimes = animeService.getAllAnimes();

        assertFalse(allAnimes.isEmpty());

    }
}
