package ru.telegram.games.werewolvessgamebot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class KeyboardFactory {
    public static ReplyKeyboard testKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add("test1");
        row.add("test2");
        return new ReplyKeyboardMarkup(List.of(row));
    }

}
