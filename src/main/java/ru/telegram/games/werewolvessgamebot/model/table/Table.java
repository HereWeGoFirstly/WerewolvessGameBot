package ru.telegram.games.werewolvessgamebot.model.table;

import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.roles.GameRole;

import java.util.List;
import java.util.Map;

@Data
public class Table {
    private Map<String, GameRole> players;
    private List<GameRole> remainingRoles;
}
