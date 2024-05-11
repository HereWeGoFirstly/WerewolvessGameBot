package ru.telegram.games.werewolvessgamebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class WerewolvessGameBotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WerewolvessGameBotApplication.class, args);
//        Table table = ctx.getBean(Table.class);
//        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя"));
//        table.refreshTable();
//        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба"));
//        table.refreshTable();
//        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза"));
//        table.refreshTable();
//        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза", 8L, "Саша(хуй его кто это)"));
//        table.refreshTable();
//        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза", 8L, "Саша(хуй его кто это)", 9L, "Ваня"));

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ctx.getBean("werewolvesBot", AbilityBot.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
