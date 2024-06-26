package ru.telegram.games.werewolvessgamebot.model.roles;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.ACCOMPLICE_DESC;

/**
 * Поддельник
 */
@Component
@Scope("prototype")
public class Accomplice extends GameRole {
    public Accomplice(Table table) {
        super(table);
        setActionPerformed(true);
    }

    @Override
    public String toString() {
        return "Поддельник";
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return ACCOMPLICE_DESC;
    }
}
