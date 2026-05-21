package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.details.DetailStock;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.DetailStockRepository;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DetailStockService {
    private DetailStockRepository detailStockRepository;

    public DetailStock addDetailStock(DetailStock detailStock) {
        if (detailStock.getId() == null || detailStockRepository.find(detailStock.getId()) != null) {
            throw new DomainValidationException("Error: Detail stock already exists.");
        }
        return detailStockRepository.save(detailStock);
    }

    public DetailStock reserveDetailStock(UUID detailId, int quantity) {
        DetailStock stock = viewDetailStockByDetailId(detailId);
        int available = stock.getAvailable();
        if (available < quantity) {
            throw new DomainValidationException("Error: Insufficient stock.");
        }
        stock.setReserved(stock.getReserved() + quantity);
        return detailStockRepository.update(stock);
    }

    public DetailStock viewDetailStock(UUID id) {
        if (detailStockRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Detail stock not found.");
        }
        return detailStockRepository.find(id);
    }

    public DetailStock viewDetailStockByDetailId(UUID detailId) {
        if (detailStockRepository.findByDetailId(detailId) == null) {
            throw new EntityNotFoundException("Error: Detail stock not found.");
        }
        return detailStockRepository.findByDetailId(detailId);
    }

    public List<DetailStock> viewDetailStockList() {
        return detailStockRepository.findAll();
    }

    public void deleteDetailStock(UUID id) {
        if (detailStockRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Detail stock not found.");
        }
        detailStockRepository.delete(id);
    }
}
