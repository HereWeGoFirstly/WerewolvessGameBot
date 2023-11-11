package ru.telegram.games.werewolvessgamebot.model.roles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.CITIZEN_DESC;

/**
 * Житель
 */
@Component
@Scope("prototype")
public class Citizen extends GameRole {
    public Citizen(Table table) {
        super(table);
        setActionPerformed(true);
    }
    @Override
    public String toString() {
        return "Житель";
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return CITIZEN_DESC;
    }
}
