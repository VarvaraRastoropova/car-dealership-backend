package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.users.Role;
import ru.rastoropova.service_layer.users.User;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User save(User car);
    User find(UUID id);
    List<User> findAll();
    List<User> findAllByRole(Role role);
    User update(User car);
    void delete(UUID id);
}
