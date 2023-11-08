package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Поддельник
 */
@Component
@Scope("prototype")
public class Accomplice extends GameRole {
    public Accomplice(Table table) {
        super(table);
    }
}
