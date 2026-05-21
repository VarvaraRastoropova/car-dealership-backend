package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.DetailStockDTO;
import ru.rastoropova.service_layer.details.DetailStock;

@Mapper(componentModel = "spring")
public interface DetailStockMapper {
    DetailStockDTO toDTO(DetailStock domain);
    DetailStock toDomain(DetailStockDTO dto);
}
