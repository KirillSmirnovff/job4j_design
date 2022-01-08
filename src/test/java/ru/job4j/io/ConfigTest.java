package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Kirill"));
        assertThat(config.value("surname"), is("Smirnov"));
            }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Kirill"));
        assertThat(config.value("surname"), is("Smirnov"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoKeyThenException() {
        String path = "./data/pair_with_no_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoValueThenException() {
        String path = "./data/pair_with_no_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoEqualSignThenException() {
        String path = "./data/pair_with_no_equal_sign.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenMultipleEqualSignThenException() {
        String path = "./data/pair_with_multiple_equal_sign.properties";
        Config config = new Config(path);
        config.load();
    }
}