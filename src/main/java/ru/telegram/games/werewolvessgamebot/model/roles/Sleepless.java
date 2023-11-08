package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Неспящий
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class Sleepless extends GameRole {
    private final Table table;
    @Override
    public void doAction() {
    }
}
