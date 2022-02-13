package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Game;
import ru.job4j.io.serialization.json.Publisher;

public class Main {
    public static void main(String[] args) {
        final ru.job4j.io.serialization.json.Game game = new Game("The Witcher 3", false, 1499, new String[]{"Action", "RPG"},
                new Publisher("CD Projekt RED"));
    }
}
