package ru.telegram.games.werewolvessgamebot.model.table;

import lombok.Data;
import ru.telegram.games.werewolvessgamebot.model.roles.*;

import java.util.List;
import java.util.Map;

@Data
public class Table {

    private static final Map<String, List<GameRole>> PRESETS = Map.of(
            "5:1", List.of(new Werewolve(), new Mason(), new Mason(), new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard()),

            "6:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter()),

            "7:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve()),

            "8:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve(), new Citizen()),

            "9:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve(), new Citizen()
            ,new Seer()));

    private Map<String, GameRole> players;
    private List<GameRole> remainingRoles;
}