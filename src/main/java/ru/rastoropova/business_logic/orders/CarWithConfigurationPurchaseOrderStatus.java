package ru.rastoropova.business_logic.orders;

public enum CarWithConfigurationPurchaseOrderStatus {
    DRAFTED,
    APPROVED,
    WAITING_PAYMENT,
    CANCELLED,
    PAID,
    WAITING_DELIVERY,
    READY,
    COMPLETED
}
