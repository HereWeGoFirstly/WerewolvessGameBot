package ru.telegram.games.werewolvessgamebot.service;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;
import ru.telegram.games.werewolvessgamebot.model.UserState;
import ru.telegram.games.werewolvessgamebot.util.Consts;

import java.util.HashMap;
import java.util.Map;

import static ru.telegram.games.werewolvessgamebot.model.UserState.*;

public class ResponseHandler {
    public static final String CHAT_STATES = "chatStates";

    private final BotProperties botProperties;
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;
    private final Map<Long, String> users = new HashMap<>();

    public ResponseHandler(SilentSender sender, DBContext db, BotProperties botProperties) {
        this.sender = sender;
        this.botProperties = botProperties;
        db.clear();
        chatStates = db.getMap(CHAT_STATES);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(botProperties.getStart().getText());
        message.setReplyMarkup(KeyboardFactory.startGameAndRules());
        sender.execute(message);
        chatStates.put(chatId, AWAITING_START_GAME);
    }

    public void replyToButtons(long chatId, Message message) {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        }
        switch (chatStates.get(chatId)) {
            case AWAITING_START_GAME -> replyOfStartGame(chatId, message);
            case AWAITING_READY_TO_PLAY -> replyOfReady(chatId, message);
        }
//        switch (chatStates.get(chatId)) {
//            case AWAITING_NAME -> replyToName(chatId, message);
//            case FOOD_DRINK_SELECTION -> replyToFoodDrinkSelection(chatId, message);
//            case PIZZA_TOPPINGS -> replyToPizzaToppings(chatId, message);
//            case AWAITING_CONFIRMATION -> replyToOrder(chatId, message);
//            default -> unexpectedMessage(chatId);
//        }
    }

    private void stopChat(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("");
        chatStates.remove(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(sendMessage);
    }

    private void replyOfStartGame(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if ("Начать игру".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Нажмите готов");
            sendMessage.setReplyMarkup(KeyboardFactory.ready());
            chatStates.put(chatId, AWAITING_READY_TO_PLAY);
        } else if ("Правила".equalsIgnoreCase((message.getText()))) {
            sendMessage.setText(Consts.RULES);
            sendMessage.setReplyMarkup(KeyboardFactory.startGame());
        } else {
            sendMessage.setText("Нажмите начать игру");
            sendMessage.setReplyMarkup(KeyboardFactory.startGameAndRules());
        }
        sender.execute(sendMessage);
    }

    private void replyOfReady(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(String.format("Привет, %s! Ожидаем других игроков", message.getFrom().getFirstName()));
        sender.execute(sendMessage);
        chatStates.put(chatId, UserState.READY_TO_PLAY);
        users.put(chatId, message.getFrom().getFirstName() + message.getFrom().getUserName());
        if (chatStates.values().stream().filter(el -> !el.equals(AWAITING_START_GAME)).count() == users.size()) {
            for (Long userId: users.keySet()) {
                SendMessage messageAllSleep = new SendMessage();
                messageAllSleep.setText("Город засыпает");
                messageAllSleep.setChatId(userId);
                sender.execute(messageAllSleep);
            }
        };
    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }
}
