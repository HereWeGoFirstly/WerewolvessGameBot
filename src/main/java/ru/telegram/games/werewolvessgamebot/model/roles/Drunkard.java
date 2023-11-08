package ru.telegram.games.werewolvessgamebot.model.roles;

/**
 * Пьяница
 */

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class Drunkard extends GameRole {
    private final Table table;
    @Override
    public void doAction() {

    }
}
