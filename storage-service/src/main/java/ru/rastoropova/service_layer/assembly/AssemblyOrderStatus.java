package ru.rastoropova.service_layer.assembly;

import java.util.HashSet;
import java.util.Set;

public enum AssemblyOrderStatus {
    FAIL(Set.of()),
    ASSEMBLED(Set.of()),
    CREATED(Set.of(ASSEMBLED));

    private Set<AssemblyOrderStatus> allowedStatuses = new HashSet<>();

    private AssemblyOrderStatus(Set<AssemblyOrderStatus> allowedStatuses) {
        this.allowedStatuses = allowedStatuses;
    }

    public boolean isAllowedStatus(AssemblyOrderStatus status) {
        return allowedStatuses.contains(status);
    }
}
