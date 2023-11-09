package ru.telegram.games.werewolvessgamebot.service;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;

import java.util.function.BiConsumer;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

@Component
@Getter
public class WerewolvesBot extends AbilityBot {
    private final ResponseHandler responseHandler;
    private final BotProperties botProperties;

    public WerewolvesBot(BotProperties botProperties, MessageService messageService) {
        super(botProperties.getToken(), botProperties.getName());
        this.botProperties = botProperties;
        responseHandler = new ResponseHandler(silent, db, botProperties, messageService);
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
    public Ability finishBot() {
        return Ability.builder()
                .name("finish")
                .info(botProperties.getFinish().getDescription())
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> responseHandler.replyToFinish(ctx.chatId()))
                .build();
    }

    public Reply replyToButtons() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> responseHandler.replyToButtons(getChatId(upd), upd.getMessage());
        return Reply.of(action, Flag.TEXT, upd -> responseHandler.userIsActive(getChatId(upd)));
    }
}
