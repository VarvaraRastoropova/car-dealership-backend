package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.CarWithConfigurationPurchaseOrderDTO;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;

@Mapper(componentModel = "spring")
public interface CarWithConfigurationPurchaseOrderMapper {
    CarWithConfigurationPurchaseOrderDTO toDTO(CarWithConfigurationPurchaseOrder domain);
    CarWithConfigurationPurchaseOrder toDomain(CarWithConfigurationPurchaseOrderDTO dto);
}
