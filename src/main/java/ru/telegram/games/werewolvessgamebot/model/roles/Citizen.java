package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.action.RoleAction;

/**
 * Житель
 */
@Component
@Scope("prototype")
public class Citizen extends GameRole {
    @Override
    public void doAction(RoleAction action) {
    }
}
