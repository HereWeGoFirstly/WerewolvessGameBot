package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;
@Component
@Scope("prototype")
public class Seer extends GameRole{

    public Seer(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Провидец";
    }
}
