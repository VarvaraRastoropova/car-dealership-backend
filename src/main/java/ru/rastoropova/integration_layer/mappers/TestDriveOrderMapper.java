package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.TestDriveOrderDTO;
import ru.rastoropova.service_layer.orders.TestDriveOrder;

@Mapper(componentModel = "spring")
public interface TestDriveOrderMapper {
    TestDriveOrderDTO toDTO(TestDriveOrder domain);
    TestDriveOrder toDomain(TestDriveOrderDTO dto);
}
