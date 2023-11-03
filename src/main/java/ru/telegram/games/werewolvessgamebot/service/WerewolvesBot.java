package ru.telegram.games.werewolvessgamebot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;

@Component
@Getter
public class WerewolvesBot extends AbilityBot {

    private final BotProperties botProperties;

    public WerewolvesBot(BotProperties botProperties) {
        super(botProperties.getToken(), botProperties.getName());
        this.botProperties = botProperties;
    }


    @Override
    public long creatorId() {
        return 1L;
    }

    public Ability startBot() {
        return Ability.builder()
                .name("start")
                .info("")
                .build();
    }
}
