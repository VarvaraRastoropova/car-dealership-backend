package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.CarPurchaseOrderDTO;
import ru.rastoropova.service_layer.orders.CarPurchaseOrder;

@Mapper(componentModel = "spring")
public interface CarPurchaseOrderMapper {
    CarPurchaseOrderDTO toDTO(CarPurchaseOrder domain);
    CarPurchaseOrder toDomain(CarPurchaseOrderDTO dto);
}
