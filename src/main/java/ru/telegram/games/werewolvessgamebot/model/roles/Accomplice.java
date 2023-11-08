package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Поддельник
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class Accomplice extends GameRole {
    private final Table table;

    @Override
    public void doAction() {

    }
}
