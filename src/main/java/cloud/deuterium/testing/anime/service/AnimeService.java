package cloud.deuterium.testing.anime.service;

import cloud.deuterium.testing.anime.model.Anime;

import java.util.List;
import java.util.UUID;

/**
 * Created by Milan Stojkovic 06-Apr-2022
 */
public interface AnimeService {

    List<Anime> getAllAnimes();

    Anime getAnimeById(UUID id);

    Anime saveAnime(Anime anime);

    void deleteAnime(UUID id);
}
