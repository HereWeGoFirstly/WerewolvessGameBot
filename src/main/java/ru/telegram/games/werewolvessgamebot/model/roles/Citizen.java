package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Житель
 */
@Component
@Scope("prototype")
public class Citizen extends GameRole {
    public Citizen(Table table) {
        super(table);
    }
    @Override
    public String toString() {
        return "Житель";
    }

    @Override
    public void doAction() {

    }
}
