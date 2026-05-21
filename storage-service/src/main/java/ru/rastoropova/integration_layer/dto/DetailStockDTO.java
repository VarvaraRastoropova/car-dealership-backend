package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import ru.rastoropova.service_layer.details.DetailType;
import java.util.UUID;

@Data
public class DetailStockDTO {
    private UUID id;
    private UUID detailId;
    private DetailType type;
    private int quantity;
    private int reserved;
}
