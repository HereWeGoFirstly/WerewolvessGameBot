package ru.telegram.games.werewolvessgamebot.model.roles;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.Map;

/**
 * Вор
 */
@Component
@Scope("prototype")
@Setter
public class Thief extends GameRole {
    private String chosenPlayer;

    public Thief(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Вор";
    }

    @Override
    public void doAction() {
        Map<String, GameRole> players = table.getPlayers();
        players.put(name, players.get(chosenPlayer));
        players.put(chosenPlayer, players.get(name));
    }
}
