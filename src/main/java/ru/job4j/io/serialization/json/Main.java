package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game("The Witcher 3", false, 1499, new String[]{"Action", "RPG"},
                new Publisher("CD Projekt RED"));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(game));
        final String gameJson =
                "{"
                    + "\"name\":\"LittleBigPlanet\","
                    + "\"pg13\":false,"
                    + "\"cost\":1499,"
                    + "\"genre\":"
                    + "[\"Puzzle\",\"Platformer\",\"SandBox\"],"
                    + "\"publisher\":"
                    + "{"
                    + "\"name\":\"SCEE\""
                    + "}"
                    + "}";
        final Game gameMod = gson.fromJson(gameJson, Game.class);
        System.out.println(gameMod);
    }
}
