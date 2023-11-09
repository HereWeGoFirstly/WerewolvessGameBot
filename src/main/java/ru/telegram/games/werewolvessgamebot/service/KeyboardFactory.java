package ru.telegram.games.werewolvessgamebot.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class KeyboardFactory {

    private final Table table;

    public static final String SEER_CHOOSE_TWO_CARDS = "Посмотреть 2 карты на столе";
    public static final String SEER_SEE_PLAYER_CARD = "Посмотреть карту игрока";
    public static final String CARD_ONE = "Карта #1";
    public static final String CARD_TWO = "Карта #2";
    public static final String CARD_THREE = "Карта #3";



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
        row.add(CARD_ONE);
        row.add(CARD_TWO);
        row.add(CARD_THREE);
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
        row.add(SEER_CHOOSE_TWO_CARDS);
        row.add(SEER_SEE_PLAYER_CARD);
        return new ReplyKeyboardMarkup(List.of(row));
    }
    public static ReplyKeyboard seerChooseOneFromTwo(Integer chosenCardNum) {
        List<Integer> cardNumbers = new ArrayList<>();
        cardNumbers.add(1);
        cardNumbers.add(2);
        cardNumbers.add(3);
        cardNumbers.remove(chosenCardNum);
        KeyboardRow row = new KeyboardRow();
        row.add("Выберите карту #" + cardNumbers.get(0));
        row.add("Выберите карту #" + cardNumbers.get(1));
        return new ReplyKeyboardMarkup(List.of(row));
    }
}
