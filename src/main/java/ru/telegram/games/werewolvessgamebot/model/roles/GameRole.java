package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * Игровая роль
 */
@Component
@Data
public abstract class GameRole {
    private static String name;
    private TeamColor teamColor;

//    public abstract void doAction();
}
