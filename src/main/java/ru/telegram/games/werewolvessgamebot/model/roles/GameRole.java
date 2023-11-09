package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.table.Table;


/**
 * Игровая роль
 */
@Data
public abstract class GameRole {
    protected final Table table;
    protected String name;
    protected TeamColor teamColor;
    private boolean isActionPerformed;

    public abstract void doAction();


    public boolean isActionPerformed() {
        return isActionPerformed;
    }
}
