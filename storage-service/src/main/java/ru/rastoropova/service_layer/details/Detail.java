package ru.rastoropova.service_layer.details;

import java.util.UUID;

public interface Detail {
    UUID getId();
    int getPrice();
    String getName();
}
