package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Хулиганка
 */
@Component
@Scope("prototype")
@Getter
@Setter
@Slf4j
public class Hooligan extends GameRole {

    private boolean isChosenCard;
    private String firstChosenPlayer;
    private String secondChosenPlayer;

    public Hooligan(Table table) {
        super(table);
    }

    @Override
    public void doAction() {
        Map<String, GameRole> players = table.getPlayers();
        Predicate<GameRole> isThereThief = role -> role.getClass().equals(Thief.class);

        if (players.values().stream().noneMatch(isThereThief) || (players.values().stream().anyMatch(isThereThief) &&
                players.values().stream().filter(isThereThief).findAny().get().isActionPerformed())) {

            GameRole secondRole = players.get(secondChosenPlayer);
            GameRole firstRole = players.get(firstChosenPlayer);

            players.put(firstChosenPlayer, secondRole);
            players.put(secondChosenPlayer, firstRole);
        }

        log.info("TABLE STATE AFTER HOOLIGAN:\n" +
                players.entrySet().stream().map(nameRole -> nameRole.getKey() +
                        " " + nameRole.getValue().getClass().getSimpleName()).toList());
    }

    @Override
    public String toString() {
        return "Хулиганка";
    }
}
