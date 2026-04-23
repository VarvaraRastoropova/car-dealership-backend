package ru.rastoropova.service_layer.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class EngineDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private int enginePower;
    private int engineVolume;
}
