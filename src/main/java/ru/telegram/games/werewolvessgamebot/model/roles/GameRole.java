package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.actions.RoleAction;
import org.springframework.stereotype.Component;


/**
 * Игровая роль
 */
@Component
@Data
public abstract class GameRole {
    private String name;
    private RoleAction action;
    private TeamColor teamColor;
}
