package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import ru.rastoropova.business_logic.cars.Configuration;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;

@Getter
public class CarWithConfigurationPurchaseOrder {
    private int id;
    private int customerId;
    private int employeeId;
    private int carModelId;
    private int carModelPrice;
    private Configuration configuration;
    private CarWithConfigurationPurchaseOrderStatus status;

    public CarWithConfigurationPurchaseOrder(int id, int customerId, int employeeId, int carModelId, int carModelPrice, Configuration configuration) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.configuration = configuration;
        this.status = CarWithConfigurationPurchaseOrderStatus.DRAFTED;
    }

    public void approve() {
        if (status != CarWithConfigurationPurchaseOrderStatus.DRAFTED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.APPROVED;
    }

    public void waitPayment() {
        if (status != CarWithConfigurationPurchaseOrderStatus.APPROVED) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT;
    }

    public void pay() {
        if (status != CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.PAID;
    }

    public void waitDelivery() {
        if (status != CarWithConfigurationPurchaseOrderStatus.PAID) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.WAITING_DELIVERY;
    }

    public void ready() {
        if (status != CarWithConfigurationPurchaseOrderStatus.WAITING_DELIVERY) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.READY;
    }

    public void complete() {
        if (status != CarWithConfigurationPurchaseOrderStatus.READY) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.COMPLETED;
    }

    public void cancel() {
        if (status != CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        status = CarWithConfigurationPurchaseOrderStatus.CANCELLED;
    }
}
