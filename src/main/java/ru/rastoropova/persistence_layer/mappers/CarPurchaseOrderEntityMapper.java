package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.CarPurchaseOrderEntity;
import ru.rastoropova.service_layer.orders.CarPurchaseOrder;

@Mapper(componentModel = "spring")
public interface CarPurchaseOrderEntityMapper {
    CarPurchaseOrderEntity toEntity(CarPurchaseOrder domain);
    CarPurchaseOrder toDomain(CarPurchaseOrderEntity entity);
}
