package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.SLEEPLESS_DESC;

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

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return SLEEPLESS_DESC;
    }
}
