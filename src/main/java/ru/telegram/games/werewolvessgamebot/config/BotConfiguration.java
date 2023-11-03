package ru.telegram.games.werewolvessgamebot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {
    @Bean
    public BotProperties botProperties() {
        return new BotProperties();
    }
}
