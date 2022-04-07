package cloud.deuterium.testing.anime.config;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 */

@Component
public class RandomService {

    public UUID randomUuid(){
        return UUID.randomUUID();
    }
}
