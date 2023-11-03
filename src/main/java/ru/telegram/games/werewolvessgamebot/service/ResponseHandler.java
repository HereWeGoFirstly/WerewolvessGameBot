package ru.telegram.games.werewolvessgamebot.service;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import ru.telegram.games.werewolvessgamebot.model.UserState;

import java.util.Map;

public class ResponseHandler {
    public static final String CHAT_STATES = "chatStates";

    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        chatStates = db.getMap(CHAT_STATES);
    }
}
