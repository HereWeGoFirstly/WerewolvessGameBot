package ru.telegram.games.werewolvessgamebot.model.table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.telegram.games.werewolvessgamebot.model.roles.*;

import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class Table {
    private final ApplicationContext context;

    private static final Map<String, List<Class<? extends GameRole>>> PRESETS = Map.of(
            "2:1", List.of(Werewolf.class, Mason.class),

            "5:1", List.of(Werewolf.class, Mason.class, Mason.class,
                    Accomplice.class, Thief.class, Hooligan.class, Sleepless.class, Drunkard.class),

            "6:1", List.of(Werewolf.class, Mason.class, Mason.class,
                    Accomplice.class, Thief.class, Hooligan.class, Sleepless.class, Drunkard.class, Hunter.class),

            "7:1", List.of(Werewolf.class, Mason.class, Mason.class,
                    Accomplice.class, Thief.class, Hooligan.class, Sleepless.class, Drunkard.class, Hunter.class, Werewolf.class),

            "8:1", List.of(Werewolf.class, Mason.class, Mason.class,
                    Accomplice.class, Thief.class, Hooligan.class, Sleepless.class, Drunkard.class, Hunter.class, Werewolf.class, Citizen.class),

            "9:1", List.of(Werewolf.class, Mason.class, Mason.class,
                    Accomplice.class, Thief.class, Hooligan.class, Sleepless.class, Drunkard.class, Hunter.class, Werewolf.class, Citizen.class
                    , Seer.class));

    @Getter
    private Map<String, GameRole> players = new HashMap<>();
    private List<GameRole> remainingRoles = new ArrayList<>();

    public void assignRoles(Map<Long, String> users) {
        List<Class<? extends GameRole>> preset = new ArrayList<>(PRESETS.entrySet().stream()
                .filter((namePresetEntry -> namePresetEntry.getKey().split(":")[0].equals("" + users.size())))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Can't find suitable preset"))
                .getValue());

        Collections.shuffle(preset);

        log.info("Preset configured : " + preset.size() + "\n" + preset.stream().map(Class::getSimpleName).toList());

        List<GameRole> activeRoles = new ArrayList<>();
        for (Class<? extends GameRole> roleClass : preset) {
            GameRole gameRole = context.getBean(roleClass);
            if (remainingRoles.size() < 3 && !roleClass.getName().equals(Werewolf.class.getName())) {
                remainingRoles.add(gameRole);
            } else {
                activeRoles.add(gameRole);
            }
        }
        log.info("activeRoles : " + activeRoles.size() + "\n" + activeRoles);
        log.info("remainingRoles : " + remainingRoles.size() + "\n" + remainingRoles);
        int i = 0;
        for (String name : users.values()) {
            players.put(name, activeRoles.get(i++));
        }
        log.info("ALL ROLES ARE ASSIGNED:\n" +
                players.entrySet().stream().map(nameRole -> nameRole.getKey() +
                        " " + nameRole.getValue().getClass().getSimpleName()).toList());
    }

    public void refreshTable() {
        players = new HashMap<>();
        remainingRoles = new ArrayList<>();
    }


}