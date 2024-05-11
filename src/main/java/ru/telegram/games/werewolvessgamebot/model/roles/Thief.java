package ru.telegram.games.werewolvessgamebot.model.roles;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static ru.telegram.games.werewolvessgamebot.util.Consts.THIEF_DESC;

/**
 * Вор
 */
@Component
@Scope("prototype")
@Setter
@Slf4j
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
        GameRole chosenPlayerRole = players.get(chosenPlayer);
        players.put(name, chosenPlayerRole);
        players.put(chosenPlayer, this);

        Optional<GameRole> optionalHooligan = table.getPlayers().values().stream()
                .filter(role -> role.getClass().equals(Hooligan.class))
                .findFirst();

        optionalHooligan.ifPresent(GameRole::doAction);

        log.info("TABLE STATE AFTER THIEF:\n" +
                players.entrySet().stream().map(nameRole -> nameRole.getKey() +
                        " " + nameRole.getValue().getClass().getSimpleName()).toList());
    }

    @Override
    public String getDesc() {
        return THIEF_DESC;
    }
}
