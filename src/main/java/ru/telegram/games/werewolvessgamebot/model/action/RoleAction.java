package ru.telegram.games.werewolvessgamebot.model.action;


import ru.telegram.games.werewolvessgamebot.model.table.Table;

import java.util.function.Consumer;

@FunctionalInterface
public interface RoleAction extends Consumer<Table> {
    @Override
    void accept(Table table);
}
