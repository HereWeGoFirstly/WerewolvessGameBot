package ru.telegram.games.werewolvessgamebot.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class KeyboardFactory {

    private final Table table;

    public static ReplyKeyboard startGameAndRules() {
        KeyboardRow row = new KeyboardRow();
        row.add("Начать игру");
        row.add("Правила");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard startGame() {
        KeyboardRow row = new KeyboardRow();
        row.add("Начать игру");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard ready() {
        KeyboardRow row = new KeyboardRow();
        row.add("Готов(а)");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard chooseOneCardFromTable() {
        KeyboardRow row = new KeyboardRow();
        row.add("Карта #1");
        row.add("Карта #2");
        row.add("Карта #3");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard choosePlayer(Set<String> players) {
        KeyboardRow row = new KeyboardRow();
        for (String player: players) {
            row.add(player);
        }
        return new ReplyKeyboardMarkup(List.of(row));
    }
    public static ReplyKeyboard chooseActionOfSeer() {
        KeyboardRow row = new KeyboardRow();
        row.add("Посмотреть 2 карты на столе");
        row.add("Посмотреть карту игрока");
        return new ReplyKeyboardMarkup(List.of(row));
    }
}
