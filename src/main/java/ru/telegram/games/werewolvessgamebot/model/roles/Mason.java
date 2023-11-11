package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.MASON_DESC;

/**
 * Масон
 */
@Component
@Scope("prototype")
public class Mason extends GameRole {

    public Mason(Table table) {
        super(table);
    }
    @Override
    public String toString() {
        return "Масон";
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return MASON_DESC;
    }
}
