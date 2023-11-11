package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.WEREWOLF_DESC;

/**
 * Оборотень
 */
@Component
@Scope("prototype")
public class Werewolf extends GameRole {
    public Werewolf(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Оборотень";
    }

    @Override
    public void doAction() {
    }
    @Override
    public String getDesc() {
        return WEREWOLF_DESC;
    }
}
