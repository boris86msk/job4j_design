package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsJoeBlack() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Rambo", 550));
        store.add(new Role("1", "Joe Black", 1000));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Joe Black"));
        assertThat(result.getSalary(), is(1000));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Kolobok", 100));
        Role result = store.findById("7");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsMrBlack() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Mr. Black", 450));
        store.add(new Role("5", "Mr. White", 350));
        store.add(new Role("5", "Mr. Red", 400));
        Role result = store.findById("5");
        assertThat(result.getRolename(), is("Mr. Black"));
        assertThat(result.getSalary(), is(450));
    }

    @Test
    public void whenReplaceThenRolenameIsMadMax() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr I", 1500));
        store.replace("1", new Role("1", "Mad Max", 500));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Mad Max"));
        assertThat(result.getSalary(), is(500));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Terminator", 800));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsRedhat() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Red hat", 300));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getSalary(), is(300));
    }
}