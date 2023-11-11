package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.HUNTER_DESC;

@Component
@Scope("prototype")
public class Hunter extends GameRole{

    public Hunter(Table table) {
        super(table);
    }
    @Override
    public String toString() {
        return "Охотник";
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return HUNTER_DESC;
    }
}
