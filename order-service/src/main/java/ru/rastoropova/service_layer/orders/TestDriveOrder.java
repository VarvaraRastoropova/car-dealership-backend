package ru.rastoropova.service_layer.orders;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
public class TestDriveOrder {
    private UUID id;
    private UUID customerId;
    private UUID carModelId;
    private String date;
    private String time;
    @Setter
    private TestDriveOrderStatus status;

    public TestDriveOrder(UUID id, UUID customerId, UUID carModelId, String date, String time) {
        this.id = id;
        this.customerId = customerId;
        this.carModelId = carModelId;
        this.date = date;
        this.time = time;
        this.status = TestDriveOrderStatus.DRAFTED;
    }
}
