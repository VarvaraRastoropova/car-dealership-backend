package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;

@Getter
public class TestDriveOrder {
    private int id;
    private int customerId;
    private int carModelId;
    private String date;
    private String time;
    private TestDriveOrderStatus status;

    public TestDriveOrder(int id, int customerId, int carModelId, String date, String time) {
        this.id = id;
        this.customerId = customerId;
        this.carModelId = carModelId;
        this.date = date;
        this.time = time;
        this.status = TestDriveOrderStatus.DRAFTED;
    }

    public void approve() {
        if (status != TestDriveOrderStatus.DRAFTED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = TestDriveOrderStatus.APPROVED;
    }

    public void complete() {
        if (status != TestDriveOrderStatus.APPROVED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = TestDriveOrderStatus.COMPLETED;
    }
}
