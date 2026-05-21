package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.DetailStockEntity;
import ru.rastoropova.service_layer.details.DetailStock;

@Mapper(componentModel = "spring")
public interface DetailStockEntityMapper {
    DetailStockEntity toEntity(DetailStock domain);
    DetailStock toDomain(DetailStockEntity entity);
}
