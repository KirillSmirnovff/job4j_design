package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsSystemAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("System Admin"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsSystemAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        store.add(new Role("1", "Advanced User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("System Admin"));
    }

    @Test
    public void whenReplaceThenRoleIsAdvancedUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        store.replace("1", new Role("1", "Advanced User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Advanced User"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        store.replace("10", new Role("10", "Advanced User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("System Admin"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsSystemAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System Admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("System Admin"));
    }
}