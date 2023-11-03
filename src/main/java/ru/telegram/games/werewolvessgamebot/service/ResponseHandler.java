package ru.telegram.games.werewolvessgamebot.service;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;
import ru.telegram.games.werewolvessgamebot.model.UserState;

import java.util.Map;

public class ResponseHandler {
    public static final String CHAT_STATES = "chatStates";

    private final BotProperties botProperties;
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;

    public ResponseHandler(SilentSender sender, DBContext db, BotProperties botProperties) {
        this.sender = sender;
        this.botProperties = botProperties;
        chatStates = db.getMap(CHAT_STATES);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(botProperties.getStart().getText());
        sender.execute(message);
        chatStates.put(chatId, AWAITING_NAME);
    }
}
