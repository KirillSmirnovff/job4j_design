package ru.job4j.io;

import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Kirill"));
        assertNull(config.value("surname"));
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
}