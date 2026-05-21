package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
public class AssemblyOrderDTO {
    private UUID id;
    private UUID sourceOrderId;
    private UUID carModelId;
    private UUID carId;
    private UUID employeeId;
    private String type;
    private String status;
    private Set<UUID> requiredComponentsIds;
}
