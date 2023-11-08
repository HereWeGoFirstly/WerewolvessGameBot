package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Пьяница
 */
@Component
@Scope("prototype")
public class Drunkard extends GameRole {
    public Drunkard(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Пьяница";
    }
}
