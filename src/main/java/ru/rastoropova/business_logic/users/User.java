package ru.rastoropova.business_logic.users;

import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
    int id;
    @Setter
    String name;
    Role role;

    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
