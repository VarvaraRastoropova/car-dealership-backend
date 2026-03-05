package ru.rastoropova.business_logic.orders;

import java.util.HashSet;
import java.util.Set;

public enum CarPurchaseOrderStatus {
    COMPLETED(Set.of()),
    READY(Set.of(COMPLETED)),
    PAID(Set.of(READY)),
    CANCELLED(Set.of()),
    WAITING_PAYMENT(Set.of(PAID, CANCELLED)),
    APPROVED(Set.of(WAITING_PAYMENT)),
    DRAFTED(Set.of(APPROVED));

    private Set<CarPurchaseOrderStatus> allowedStatuses = new HashSet<>();

    private CarPurchaseOrderStatus(Set<CarPurchaseOrderStatus> allowedStatuses) {
        this.allowedStatuses = allowedStatuses;
    }

    public boolean isAllowedStatus(CarPurchaseOrderStatus status) {
        return allowedStatuses.contains(status);
    }
}
