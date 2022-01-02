package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        Map<Integer, String> previousMap = previous.stream().
                collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> currentMap = current.stream().
                collect(Collectors.toMap(User::getId, User::getName));
        result.setDeleted(
                (int) previousMap.keySet().stream().
                        filter(s -> !currentMap.containsKey(s)).
                        count()
        );
        result.setAdded(
                (int) currentMap.keySet().stream().
                        filter(s -> !previousMap.containsKey(s)).
                        count()
        );
        result.setChanged(
                (int) previousMap.keySet().stream().
                        filter(s -> currentMap.containsKey(s)
                                && !previousMap.get(s).equals(currentMap.get(s))).
                        count()
        );
        return result;
    }
}