package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TestDriveOrder {
    private int id;
    private int customerId;
    private int carModelId;
    private String date;
    private String time;
    @Setter
    private TestDriveOrderStatus status;

    public TestDriveOrder(int id, int customerId, int carModelId, String date, String time) {
        this.id = id;
        this.customerId = customerId;
        this.carModelId = carModelId;
        this.date = date;
        this.time = time;
        this.status = TestDriveOrderStatus.DRAFTED;
    }
}
