package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Хулиганка
 */
@Component
@Scope("prototype")
public class Hooligan extends GameRole {

    public Hooligan(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Хулиганка";
    }
}
