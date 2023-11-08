package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.action.RoleAction;
import ru.telegram.games.werewolvessgamebot.model.table.Table;


/**
 * Игровая роль
 */
@Data
public abstract class GameRole {
    private final Table table;
    private static String name;
    private TeamColor teamColor;
    private boolean isActionPerformed;

    public void doAction(RoleAction action) {
        action.accept(table);
    }

    public String getRusName() {
        return null;
    }

    public boolean isActionPerformed() {
        return isActionPerformed;
    }
}
