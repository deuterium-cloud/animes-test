package cloud.deuterium.testing.anime.service;

import cloud.deuterium.testing.anime.model.Anime;
import cloud.deuterium.testing.anime.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Milan Stojkovic 06-Apr-2022
 */

@RequiredArgsConstructor
@Service
public class AnimeServiceImpl implements AnimeService{

    private final AnimeRepository animeRepository;

    @Override
    public List<Anime> getAllAnimes() {
        return animeRepository.getAll();
    }

    @Override
    public Anime getAnimeById(UUID id) {
        return animeRepository.get(id);
    }

    @Override
    public Anime saveAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public void deleteAnime(UUID id) {
        animeRepository.delete(id);
    }
}
