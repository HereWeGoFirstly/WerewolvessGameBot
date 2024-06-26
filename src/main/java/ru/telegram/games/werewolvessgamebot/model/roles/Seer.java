package ru.telegram.games.werewolvessgamebot.model.roles;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.table.Table;

import static ru.telegram.games.werewolvessgamebot.util.Consts.SEER_DESC;

@Component
@Scope("prototype")
@Getter
@Setter
public class Seer extends GameRole{

    private boolean isChosenFirstCard;
    private ActionType actionType;
    private GameRole firstChosenCard;
    private GameRole secondChosenCard;

    public Seer(Table table) {
        super(table);
    }

    @Override
    public String toString() {
        return "Провидец";
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getDesc() {
        return SEER_DESC;
    }

    public enum ActionType {
        TWO_CARDS, PLAYER_CARD;
    }
}


