package ru.telegram.games.werewolvessgamebot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;

@Component
@Getter
public class WerewolvesBot extends AbilityBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    public WerewolvesBot() {
        super("", "");


    }

    @Override
    public long creatorId() {
        return 0;
    }
}
