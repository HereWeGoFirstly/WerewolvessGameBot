package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

@Component
@Scope("prototype")
public class Hunter extends GameRole{

    public Hunter(Table table) {
        super(table);
    }

}
