package cloud.deuterium.testing.anime.repository;

import cloud.deuterium.testing.anime.config.RandomService;
import cloud.deuterium.testing.anime.model.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Milan Stojkovic 06-Apr-2022
 */

@RequiredArgsConstructor
@Repository
public class AnimeRepository {

    private List<Anime> animes = new ArrayList<>();
    private final Clock clock;
    private final RandomService random;

    public List<Anime> getAll(){
        return animes;
    }

    public Anime get(UUID id){
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(new Anime());
    }

    public Anime save(Anime anime){
        anime.setId(random.randomUuid());
        anime.setTimestamp(Instant.now(clock));
        animes.add(anime);
        return anime;
    }

    public void delete(UUID id){
        animes = animes.stream()
                .filter(anime -> !anime.getId().equals(id))
                .toList();
    }
}
