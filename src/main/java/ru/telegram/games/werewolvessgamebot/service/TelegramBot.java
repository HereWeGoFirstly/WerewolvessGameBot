package ru.telegram.games.werewolvessgamebot.service;

import ru.telegram.games.werewolvessgamebot.config.WerewolvesBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final WerewolvesBot botConfig;
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
