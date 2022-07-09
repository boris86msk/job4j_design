package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutOneElement() {
        SimpleMap<User, String> map = new SimpleMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 15);
        User user = new User("Bob", 2, calendar);
        assertTrue(map.put(user, "10000"));
        assertFalse(map.put(user, "10000"));
    }

    @Test
    public void wenPutTwoElements() {
        SimpleMap<User, String> map = new SimpleMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 15);
        User user1 = new User("Bob", 2, calendar);
        User user2 = new User("Ivan", 1, calendar);
        assertNotEquals(user1.hashCode(), user2.hashCode());
        map.put(user1, "400");
        assertTrue(map.put(user2, "500"));
    }

    @Test
    public void iterator() {
        SimpleMap<User, String> map = new SimpleMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 15);
        User user1 = new User("Bob", 2, calendar);
        User user2 = new User("Ivan", 1, calendar);
        map.put(user1, "200");
        map.put(user2, "350");
        Iterator<User> iterator = map.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void wenGetТotExistingKey() {
        SimpleMap<User, String> map = new SimpleMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 15);
        User user1 = new User("Bob", 2, calendar);
        User user2 = new User("Ivan", 1, calendar);
        User user3 = new User("Jon", 0, calendar);
        map.put(user1, "200");
        map.put(user2, "350");
        assertThat(map.get(user2), is("350"));
        assertNull(map.get(user3));
    }

    @Test
    public void whenRemoveOneElement() {
        SimpleMap<User, String> map = new SimpleMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 15);
        User user = new User("Bob", 2, calendar);
        User user2 = new User("Jon", 0, calendar);
        map.put(user2, "0");
        assertTrue(map.put(user, "10000"));
        assertTrue(map.remove(user));
        assertNull(map.get(user));
    }
}