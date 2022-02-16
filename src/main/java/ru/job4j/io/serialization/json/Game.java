package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Game {
    private final String name;
    private final boolean pg13;
    private final int cost;
    private final String[] genre;
    private final Publisher publisher;

    public Game(String name, boolean pg13, int cost, String[] genre, Publisher publisher) {
        this.name = name;
        this.pg13 = pg13;
        this.cost = cost;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public boolean isPg13() {
        return pg13;
    }

    public int getCost() {
        return cost;
    }

    public String[] getGenre() {
        return genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "Game{"
                + "name='" + name + '\''
                + ", pg13=" + pg13
                + ", cost=" + cost
                + ", genre=" + Arrays.toString(genre)
                + ", publisher=" + publisher
                + '}';
    }
}
