package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Неспящий
 */
@Component
@Scope("prototype")
public class Sleepless extends GameRole {
    public Sleepless(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Неспящий";
    }
}
