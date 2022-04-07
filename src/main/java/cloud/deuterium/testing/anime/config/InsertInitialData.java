package cloud.deuterium.testing.anime.config;

import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.repository.AnimeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by Milan Stojkovic 06-Apr-2022
 */

@Component
public class InsertInitialData implements CommandLineRunner {

    private final AnimeRepository animeRepository;

    public InsertInitialData(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of(new Anime(null, "Berserk", 45, null),
                        new Anime(null, "Basilisk", 24, null),
                        new Anime(null, "Death Note", 24, null),
                        new Anime(null, "Fullmetal Alchemist", 51, null))
                .forEach(animeRepository::save);
    }
}
