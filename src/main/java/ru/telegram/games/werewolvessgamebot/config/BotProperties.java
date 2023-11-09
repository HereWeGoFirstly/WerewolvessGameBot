package ru.telegram.games.werewolvessgamebot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bot")
@Getter
@Setter
public class BotProperties {
    private String name;
    private String token;
    private Start start;
    private Finish finish;

    @Getter
    @Setter
    public static class Start {
        private String description;
        private String text;
    }
    @Getter
    @Setter
    public static class Finish {
        private String description;
        private String text;
    }
}
