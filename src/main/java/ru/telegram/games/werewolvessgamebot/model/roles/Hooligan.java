package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Getter;
import lombok.Setter;
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

        if (players.values().stream().anyMatch(isThereThief) &&
                players.values().stream().filter(isThereThief).findAny().get().isActionPerformed()) {
            players.put(firstChosenPlayer, players.get(secondChosenPlayer));
            players.put(secondChosenPlayer, players.get(firstChosenPlayer));
        }
    }

    @Override
    public String toString() {
        return "Хулиганка";
    }
}
