package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.rastoropova.persistence_layer.entities.CarModelEntity;
import ru.rastoropova.persistence_layer.entities.CarPurchaseOrderEntity;
import ru.rastoropova.persistence_layer.entities.UserEntity;
import ru.rastoropova.service_layer.orders.CarPurchaseOrder;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarPurchaseOrderEntityMapper {
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "getUserFromId")
    @Mapping(target = "employee", source = "employeeId", qualifiedByName = "getUserFromId")
    @Mapping(target = "carModel", source = "carModelId", qualifiedByName = "getCarModelFromId")
    CarPurchaseOrderEntity toEntity(CarPurchaseOrder domain);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "carModelId", source = "carModel.id")
    CarPurchaseOrder toDomain(CarPurchaseOrderEntity entity);

    @Named("getUserFromId")
    default UserEntity getUserFromId(UUID id) {
        if (id == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    @Named("getCarModelFromId")
    default CarModelEntity getCarModelFromId(UUID id) {
        if (id == null) {
            return null;
        }
        CarModelEntity model = new CarModelEntity();
        model.setId(id);
        return model;
    }
}
