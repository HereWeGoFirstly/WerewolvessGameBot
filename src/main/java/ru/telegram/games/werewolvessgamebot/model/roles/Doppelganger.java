package ru.telegram.games.werewolvessgamebot.model.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.action.RoleAction;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Двойник
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class Doppelganger extends GameRole {
    private final Table table;
    @Override
    public void doAction(RoleAction<Table> action) {

    }
}
