package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.UserEntity;
import ru.rastoropova.service_layer.users.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User domain);
    User toDomain(UserEntity entity);
}
