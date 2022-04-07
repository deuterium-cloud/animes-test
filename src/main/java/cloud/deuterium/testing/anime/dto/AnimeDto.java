package cloud.deuterium.testing.anime.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@Getter
@Setter
public class AnimeDto {
    UUID id;
    String name;
    int episodes;
    Instant timestamp;
}
