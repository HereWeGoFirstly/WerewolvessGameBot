package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Житель
 */
@Component
@Scope("prototype")
public class Citizen extends GameRole {
    @Override
    public void doAction() {
    }
}
