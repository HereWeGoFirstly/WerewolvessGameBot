package ru.telegram.games.werewolvessgamebot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.games.werewolvessgamebot.model.roles.*;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final Table table;

    public SendMessage actionMessageByName(String name, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        GameRole role = table.getPlayers().get(name);

        if (role.getClass().equals(Werewolf.class)) {
            if (table.getPlayers().values().stream().filter(el -> el.getClass().equals(Werewolf.class)).count() > 1) {
                sendMessage.setText(String.format("Ваш второй оборотень: %s",
                        table.getPlayers().entrySet().stream().filter(el ->
                                        el.getValue().getClass().equals(Werewolf.class) && !el.getKey().equals(name))
                                .map(Map.Entry::getKey).findAny().orElse("Бот сломался")));
            } else {
                sendMessage.setText("Выберите действие");
                sendMessage.setReplyMarkup(KeyboardFactory.chooseOneCardFromTable());

            }

        } else if (role.getClass().equals(Mason.class)) {
            if (table.getPlayers().values().stream().filter(el -> el.getClass().equals(Mason.class)).count() > 1) {
                sendMessage.setText(String.format("Ваш второй массон: %s",
                        table.getPlayers().entrySet().stream().filter(el ->
                                        el.getValue().getClass().equals(Mason.class) && !el.getKey().equals(name))
                                .map(Map.Entry::getKey)
                                .findAny().orElse("Бот сломался")));
            } else {
                sendMessage.setText("Выберите действие");
                sendMessage.setReplyMarkup(KeyboardFactory.chooseOneCardFromTable());
            }

        } else if (role.getClass().equals(Thief.class)) {
            sendMessage.setText("Выберите действие");
            sendMessage.setReplyMarkup(KeyboardFactory.choosePlayer(table.getPlayers().keySet()));


        } else if (role.getClass().equals(Seer.class)) {
            sendMessage.setText("Выберите действие");
            sendMessage.setReplyMarkup(KeyboardFactory.chooseActionOfSeer());

        } else if (role.getClass().equals(Hooligan.class)) {
            sendMessage.setReplyMarkup(KeyboardFactory.choosePlayer(table.getPlayers().keySet()));
        } else if (role.getClass().equals(Accomplice.class)) {
            if (table.getPlayers().values().stream().filter(el -> el.getClass().equals(Werewolf.class)).count() > 1) {
                sendMessage.setText(String.format("Ваши оборотни %s",
                        table.getPlayers().entrySet().stream().filter(el ->
                                        el.getValue().getClass().equals(Werewolf.class)).map(roleEntry -> roleEntry.getKey().toString())
                                .reduce((role1, role2) -> role1 + " и " + role2)));
            } else {
                sendMessage.setText(String.format("Ваш оборотень: %s", table.getPlayers().entrySet().stream()
                        .filter(el -> el.getValue().getClass().equals(Werewolf.class))
                        .map(roleEntry -> roleEntry.getKey().split("@")[0]).findAny().orElse("Бот сломался")));
            }
        }
//        } else if (role.getClass().equals(Sleepless.class)) {
//            sendMessage.setText(String.format("Ваша роль: %s", table.getPlayers().get(name).getRusName()));
        else {
            sendMessage.setText("Дождитесь действий игроков");
        }
        return sendMessage;
    }

    public SendMessage delegateAction(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        return null;
    }

    public void initializeTable(Map<Long, String> users) {
        table.assignRoles(users);
    }

    public String getRoleByName(String roleName) {
        return table.getPlayers().get(roleName).toString();
    }
}
