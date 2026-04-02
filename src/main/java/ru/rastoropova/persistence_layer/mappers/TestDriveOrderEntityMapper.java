package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.TestDriveOrderEntity;
import ru.rastoropova.service_layer.orders.TestDriveOrder;

@Mapper(componentModel = "spring")
public interface TestDriveOrderEntityMapper {
    TestDriveOrderEntity toEntity(TestDriveOrder domain);
    TestDriveOrder toDomain(TestDriveOrderEntity entity);
}
