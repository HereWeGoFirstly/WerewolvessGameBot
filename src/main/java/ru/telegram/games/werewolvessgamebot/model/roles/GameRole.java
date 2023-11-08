package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;


/**
 * Игровая роль
 */
@Data
public abstract class GameRole {
    private static String name;
    private TeamColor teamColor;

    public abstract void doAction();
}
