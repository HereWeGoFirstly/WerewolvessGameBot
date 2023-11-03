package ru.telegram.games.werewolvessgamebot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

@Component
@Getter
public class WerewolvesBot extends AbilityBot {
    private final ResponseHandler responseHandler;
    private final BotProperties botProperties;

    public WerewolvesBot(BotProperties botProperties) {
        super(botProperties.getToken(), botProperties.getName());
        this.botProperties = botProperties;
        responseHandler = new ResponseHandler(silent, db, botProperties);
    }


    @Override
    public long creatorId() {
        return 1L;
    }

    public Ability startBot() {
        return Ability.builder()
                .name("start")
                .info(botProperties.getStart().getDescription())
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> responseHandler.replyToStart(ctx.chatId()))
                .build();
    }
}
