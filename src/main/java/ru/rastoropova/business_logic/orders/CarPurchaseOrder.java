package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;

@Getter
public class CarPurchaseOrder {
    private int id;
    private int customerId;
    private int employeeId;
    private int carModelId;
    private int carModelPrice;
    private CarPurchaseOrderStatus status;

    public CarPurchaseOrder(int id, int customerId, int employeeId, int carModelId, int carModelPrice) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.status = CarPurchaseOrderStatus.DRAFTED;
    }

    public void approve() {
        if (status != CarPurchaseOrderStatus.DRAFTED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.APPROVED;
    }

    public void waitPayment() {
        if (status != CarPurchaseOrderStatus.APPROVED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.WAITING_PAYMENT;
    }

    public void pay() {
        if (status != CarPurchaseOrderStatus.WAITING_PAYMENT) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.PAID;
    }

    public void ready() {
        if (status != CarPurchaseOrderStatus.PAID) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.READY;
    }

    public void complete() {
        if (status != CarPurchaseOrderStatus.READY) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.COMPLETED;
    }

    public void cancel() {
        if (status != CarPurchaseOrderStatus.WAITING_PAYMENT) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarPurchaseOrderStatus.CANCELLED;
    }
}
