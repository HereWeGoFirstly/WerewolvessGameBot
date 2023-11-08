package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Вор
 */
@Component
@Scope("prototype")
public class Thief extends GameRole {
    public Thief(Table table) {
        super(table);
    }
}
