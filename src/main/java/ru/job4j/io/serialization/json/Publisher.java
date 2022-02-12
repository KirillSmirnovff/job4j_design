package ru.job4j.io.serialization.json;

public class Publisher {
    private final String name;

    public Publisher(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{"
                + "name='" + name + '\''
                + '}';
    }
}
