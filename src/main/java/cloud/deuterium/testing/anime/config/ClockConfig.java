package cloud.deuterium.testing.anime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Created by Milan Stojkovic 07-Apr-2022
 * https://blog.ttulka.com/how-to-test-date-and-time-in-spring-boot
 */

@Configuration
public class ClockConfig {

    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }
}
