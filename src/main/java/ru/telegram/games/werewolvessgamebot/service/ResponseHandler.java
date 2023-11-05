package ru.telegram.games.werewolvessgamebot.service;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import ru.telegram.games.werewolvessgamebot.config.BotProperties;
import ru.telegram.games.werewolvessgamebot.model.UserState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.telegram.games.werewolvessgamebot.model.UserState.AWAITING_NAME;

public class ResponseHandler {
    public static final String CHAT_STATES = "chatStates";

    private final BotProperties botProperties;
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;
    private final Map<Long, String> users = new ConcurrentHashMap<>();

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

    public void replyToButtons(long chatId, Message message) {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        }
        testKeyboards(chatId, message);
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
        sendMessage.setText("Thank you for your order. See you soon! \n Press /start to order again");
        chatStates.remove(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(sendMessage);
    }

    private void testKeyboards(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if ("Начать игру".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Ожидаем готовности других игроков");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else {
            sendMessage.setText("Нажмите начать игру");
            sendMessage.setReplyMarkup(KeyboardFactory.startGame());
        }
        sender.execute(sendMessage);

    }

//    private void promptWithKeyboardForState(long chatId, String text, ReplyKeyboard YesOrNo, UserState awaitingReorder) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(text);
//        sendMessage.setReplyMarkup(YesOrNo);
//        sender.execute(sendMessage);
//        chatStates.put(chatId, awaitingReorder);
//    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }
}
