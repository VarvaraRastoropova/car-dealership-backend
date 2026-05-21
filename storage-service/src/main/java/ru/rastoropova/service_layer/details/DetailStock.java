package ru.rastoropova.service_layer.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DetailStock {
    private UUID id;
    private UUID detailId;
    private DetailType type;
    @Setter
    private int quantity;
    @Setter
    private int reserved;

    public int getAvailable() {
        return quantity - reserved;
    }
}
