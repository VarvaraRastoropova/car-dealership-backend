package ru.rastoropova.business_logic.orders;

import java.util.HashSet;
import java.util.Set;

public enum CarWithConfigurationPurchaseOrderStatus {
    COMPLETED(Set.of()),
    READY(Set.of(COMPLETED)),
    WAITING_DELIVERY(Set.of(READY)),
    PAID(Set.of(WAITING_DELIVERY)),
    CANCELLED(Set.of()),
    WAITING_PAYMENT(Set.of(PAID, CANCELLED)),
    APPROVED(Set.of(WAITING_PAYMENT)),
    DRAFTED(Set.of(APPROVED));

    private Set<CarWithConfigurationPurchaseOrderStatus> allowedStatuses = new HashSet<>();

    private CarWithConfigurationPurchaseOrderStatus(Set<CarWithConfigurationPurchaseOrderStatus> allowedStatuses) {
        this.allowedStatuses = allowedStatuses;
    }

    public boolean isAllowedStatus(CarWithConfigurationPurchaseOrderStatus status) {
        return allowedStatuses.contains(status);
    }
}
