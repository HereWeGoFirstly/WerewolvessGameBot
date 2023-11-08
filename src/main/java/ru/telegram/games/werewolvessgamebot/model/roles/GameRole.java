package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.action.RoleAction;
import ru.telegram.games.werewolvessgamebot.model.table.Table;


/**
 * Игровая роль
 */
@Data
public abstract class GameRole {
    private static String name;
    private TeamColor teamColor;

    public abstract void doAction(RoleAction<Table> action);
}
