package cloud.deuterium.testing.anime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by Milan Stojkovic 06-Apr-2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Anime {
    private UUID id;
    private String name;
    private int episodes;
    private Instant timestamp;
}
