package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.details.DetailType;
import java.util.UUID;

@Entity
@Table(name = "detail_stocks")
@Getter
@Setter
public class DetailStockEntity extends BaseEntity {
    @Column(name = "detail_id", nullable = false)
    private UUID detailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DetailType type;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "reserved", nullable = false)
    private int reserved;
}
