package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.DetailStock;
import java.util.List;
import java.util.UUID;

public interface DetailStockRepository {
    DetailStock save(DetailStock car);
    DetailStock find(UUID id);
    DetailStock findByDetailId(UUID detailId);
    List<DetailStock> findAll();
    DetailStock update(DetailStock car);
    void delete(UUID id);
}
