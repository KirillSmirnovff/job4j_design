package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game("The Witcher 3", false, 1499, new String[]{"Action", "RPG"},
                new Publisher("CD Projekt RED"));
        JSONObject jsonPublisher = new JSONObject("{\"name\":\"CD Projekt RED\"}");

        List<String> list = new ArrayList<>();
        list.add("Action");
        list.add("RPG");
        JSONArray jsonGenres = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", game.getName());
        jsonObject.put("pg13", game.isPg13());
        jsonObject.put("cost", game.getCost());
        jsonObject.put("genre", jsonGenres);
        jsonObject.put("publisher", jsonPublisher);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(game));
    }
}
