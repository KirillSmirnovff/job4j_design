package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean pg13;
    @XmlAttribute
    private int cost;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private String[] genre;
    private Publisher publisher;

    public Game() { }

    public Game(String name, boolean pg13, int cost, String[] genre, Publisher publisher) {
        this.name = name;
        this.pg13 = pg13;
        this.cost = cost;
        this.genre = genre;
        this.publisher = publisher;
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
