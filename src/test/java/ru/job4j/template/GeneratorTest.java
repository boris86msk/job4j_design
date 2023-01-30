package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    public void whenAnExtraKey() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Boris Pokidov");
        map.put("subject", "you");
        map.put("something", "*/*/**");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenKeIsNotInMap() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Boris Pokidov");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplatIsEmpty() {
        Generator generator = new SimpleGenerator();
        String template = "";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Boris Pokidov");
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapIsEmpty() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapIs() {
        Generator generator = new SimpleGenerator();
        String template = "I am a {name}, Who are {subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Boris Pokidov");
        map.put("subject", "you");
        Pattern pattern = Pattern.compile("\\$\\{\\w+}");
        Matcher matcher = pattern.matcher(template);
        assertThat(matcher.find()).isTrue();
    }

}