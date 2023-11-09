package ru.telegram.games.werewolvessgamebot.model.roles;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

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
        GameRole thief = players.get(name);
        GameRole chosenPlayerRole = players.get(chosenPlayer);

        players.put(name, chosenPlayerRole);
        players.put(chosenPlayer, thief);

        Optional<GameRole> optionalHooligan = table.getPlayers().values().stream()
                .filter(role -> role.getClass().getSimpleName().equals(Hooligan.class.getSimpleName()))
                .findFirst();

        optionalHooligan.ifPresent(GameRole::doAction);
    }
}
