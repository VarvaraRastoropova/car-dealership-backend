package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rastoropova.persistence_layer.entities.UserEntity;
import ru.rastoropova.service_layer.users.Role;
import ru.rastoropova.service_layer.users.User;
import java.util.List;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    List<User> findAllByRole(Role role);
}
