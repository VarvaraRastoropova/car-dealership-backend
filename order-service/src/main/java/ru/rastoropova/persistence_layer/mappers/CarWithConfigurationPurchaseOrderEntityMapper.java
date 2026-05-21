package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.CarWithConfigurationPurchaseOrderEntity;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;

@Mapper(componentModel = "spring")
public interface CarWithConfigurationPurchaseOrderEntityMapper {
    CarWithConfigurationPurchaseOrderEntity toEntity(CarWithConfigurationPurchaseOrder domain);
    CarWithConfigurationPurchaseOrder toDomain(CarWithConfigurationPurchaseOrderEntity entity);
}
