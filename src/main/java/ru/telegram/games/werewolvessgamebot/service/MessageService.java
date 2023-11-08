package ru.telegram.games.werewolvessgamebot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageService {

    public SendMessage actionMessageByName(String value, Long key) {
        return null;
    }

    public SendMessage delegateAction(long chatId, Message message) {
        return null;
    }
}
