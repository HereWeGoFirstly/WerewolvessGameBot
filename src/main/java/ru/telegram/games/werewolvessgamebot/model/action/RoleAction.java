package ru.telegram.games.werewolvessgamebot.model.action;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@FunctionalInterface
public interface RoleAction<Table> extends Consumer<Table> {
    @Override
    void accept(Table t);
}
