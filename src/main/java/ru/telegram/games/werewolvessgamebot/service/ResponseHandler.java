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
    private final MessageService messageService;
    private final Map<Long, UserState> chatStates;
    private final Map<Long, String> users = new HashMap<>();

    public ResponseHandler(SilentSender sender, DBContext db, BotProperties botProperties, MessageService messageService) {
        this.sender = sender;
        this.botProperties = botProperties;
        this.messageService = messageService;
        db.clear();
        chatStates = db.getMap(CHAT_STATES);
    }

    public void replyToStart(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(botProperties.getStart().getText());
        sendMessage.setReplyMarkup(KeyboardFactory.startGameAndRules());
        sender.execute(sendMessage);
        chatStates.put(chatId, AWAITING_START_GAME);
    }

    public void replyToFinish(long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(botProperties.getFinish().getText())
                .build();
        sender.execute(sendMessage);
    }

    public void replyToButtons(long chatId, Message message) {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        }
        switch (chatStates.get(chatId)) {
            case AWAITING_START_GAME -> replyOfStartGame(chatId, message);
            case AWAITING_READY_TO_PLAY -> replyOfReady(chatId, message);
            case PLAYING -> actionOfRole(chatId, message);
        }
    }

    /**
     * bot sends incorrect command message
     *
     * @param chatId - id of chat
     */
//    private void unexpectedMessage(long chatId) {
//        SendMessage sendMessage = SendMessage.builder()
//                .chatId(chatId)
//                .text("Вы ввели некорректную команду")
//                .build();
//        sender.execute(sendMessage);
//    }

    /**
     * stopping chat by command /stop
     *
     * @param chatId - id of chat
     */
    private void stopChat(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Игра завершена");
        chatStates.remove(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(sendMessage);
    }

    /**
     * Options for users:
     * Rules: gives you rules of the game
     * Start game: game allows to start game
     *
     * @param chatId  - id of chat
     * @param message - message from user to bot
     */
    private void replyOfStartGame(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if ("Начать игру".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Нажмите готов(а)");
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

    /**
     * Method which wait for players tap Ready button
     *
     * @param chatId  - id of chat
     * @param message - message from user to bot
     */
    private void replyOfReady(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(String.format("Привет, %s! Ожидаем других игроков", message.getFrom().getFirstName()));
        sender.execute(sendMessage);
        chatStates.put(chatId, READY_TO_PLAY);
        users.put(chatId, getUniqName(message));
        long countAwaitingUsers = chatStates.values().stream().filter(el -> !el.equals(AWAITING_START_GAME)).count();
        if (countAwaitingUsers == users.size() && countAwaitingUsers >= 3) {

            messageService.initializeTable(users);
            for (Long currentChatId : users.keySet()) {
                SendMessage messageAllSleep = new SendMessage();
                chatStates.put(currentChatId, PLAYING); //выставление всем ролям состояния: Играет
                messageAllSleep.setText("Город засыпает");
                messageAllSleep.setChatId(currentChatId);
                sender.execute(messageAllSleep);
                sender.execute(SendMessage
                        .builder().chatId(currentChatId).text(
                                String.format("Ваша роль: %s", messageService.getRoleByName(users.get(currentChatId)))).build());
                sender.execute(messageService.actionMessageByName(users.get(currentChatId), currentChatId));

            }
        }

    }



    /**
     * Method for action of role
     *
     * @param chatId - id of chat
     */
    private void actionOfRole(long chatId, Message message) {
        sender.execute(messageService.delegateAction(chatId, message, users.get(chatId)));

//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText("Выберите одну из 3 карт на столе");
//        sendMessage.setChatId(chatId);
//        sendMessage.setReplyMarkup(KeyboardFactory.chooseOneCardFromTable());
    }


    private static String getUniqName(Message message) {
        return message.getFrom().getFirstName() + " @" + message.getFrom().getUserName();
    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }


}
