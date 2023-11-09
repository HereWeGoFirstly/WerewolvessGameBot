package ru.telegram.games.werewolvessgamebot.model.roles;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

/**
 * Хулиганка
 */
@Component
@Scope("prototype")
@Getter
@Setter
public class Hooligan extends GameRole {

    private boolean isChosenCard;
    private String firstChosenCard;
    private String secondChosenCard;
    public Hooligan(Table table) {
        super(table);
    }



    @Override
    public void doAction() {
    }

    @Override
    public String toString() {
        return "Хулиганка";
    }
}
