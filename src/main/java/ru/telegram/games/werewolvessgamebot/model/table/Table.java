package ru.telegram.games.werewolvessgamebot.model.table;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.roles.*;

import java.util.*;

@Data
@Component
public class Table {

    private static final Map<String, List<GameRole>> PRESETS = Map.of(
            "5:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard()),

            "6:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter()),

            "7:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve()),

            "8:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve(), new Citizen()),

            "9:1", List.of(new Werewolve(), new Mason(), new Mason(),
                    new Accomplice(), new Thief(), new Hooligan(), new Sleepless(), new Drunkard(), new Hunter(), new Werewolve(), new Citizen()
                    , new Seer()));

    private Map<String, GameRole> players = new HashMap<>();
    private List<GameRole> remainingRoles = new ArrayList<>();

    public void assignRoles(Map<Long, String> users) {
        List<GameRole> preset = new ArrayList<>(PRESETS.entrySet().stream()
                .filter((namePresetEntry -> namePresetEntry.getKey().split(":")[0].equals("" + users.size())))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Can't find suitable preset"))
                .getValue());

        Collections.shuffle(preset);
//        preset.forEach((x) -> {
//            System.out.print(x.getClass().getSimpleName() + " ");
//        });

        for (int i = 0; i < preset.size(); i++) {
            if (remainingRoles.size() < 3 && !preset.get(i).getClass().equals(Werewolve.class)) {
                remainingRoles.add(preset.remove(i));
            }
        }

        remainingRoles.forEach((x) -> {
            System.out.print(x.getClass().getSimpleName() + " ");
        });
        System.out.println();
        remainingRoles = new ArrayList<>();
//        for (Map.Entry<Long, String> idName : users.entrySet()) {
//
//            String name = idName.getValue();
//            players.put(name, preset.get(i));
//            preset.remove(i);
//        }
//        System.out.println(preset.size());

    }

    public static void main(String[] args) {
        Table table = new Table();
        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя"));
//        Map<String, GameRole> players1 = table.getPlayers();
        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба"));
        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза"));
        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза", 8L, "Саша(хуй его кто это)"));
        table.assignRoles(Map.of(1L, "Гена", 2L, "Сережа", 3L, "Сергей", 4L, "Паша", 5L, "Валя", 6L, "Люба", 7L, "Лиза", 8L, "Саша(хуй его кто это)", 9L, "Ваня"));
    }
}