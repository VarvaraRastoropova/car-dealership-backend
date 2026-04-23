package ru.rastoropova.service_layer.orders;

import java.util.HashSet;
import java.util.Set;

public enum TestDriveOrderStatus {
    COMPLETED(Set.of()),
    CANCELLED(Set.of()),
    APPROVED(Set.of(COMPLETED)),
    DRAFTED(Set.of(APPROVED));

    private Set<TestDriveOrderStatus> allowedStatuses = new HashSet<>();

    private TestDriveOrderStatus(Set<TestDriveOrderStatus> allowedStatuses) {
        this.allowedStatuses = allowedStatuses;
    }

    public boolean isAllowedStatus(TestDriveOrderStatus status) {
        return allowedStatuses.contains(status);
    }
}
