package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Двойник
 */
@Component
@Scope("prototype")
public class Doppelganger extends GameRole {
    public Doppelganger(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Двойник";
    }

    @Override
    public void doAction() {

    }
}
