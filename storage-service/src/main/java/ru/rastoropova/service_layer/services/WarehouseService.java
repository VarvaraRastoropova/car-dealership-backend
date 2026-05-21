package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.integration_layer.dto.AssemblyOrderDTO;
import ru.rastoropova.integration_layer.mappers.AssemblyOrderMapper;
import ru.rastoropova.service_layer.assembly.AssemblyOrder;
import ru.rastoropova.service_layer.assembly.AssemblyOrderStatus;
import ru.rastoropova.service_layer.assembly.OriginalOrderType;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.details.DetailStock;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.AssemblyOrderRepository;
import ru.rastoropova.service_layer.repositories.CarRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WarehouseService {
    private AssemblyOrderRepository assemblyOrderRepository;
    private CarRepository carRepository;
    private DetailStockService detailStockService;
    private AssemblyOrderMapper mapper;

    public AssemblyOrder createAssemblyOrder(AssemblyOrderDTO dto) {
        if (assemblyOrderRepository.find(dto.getId()) != null) {
            throw new DomainValidationException("Error: Assembly order already exists.");
        }
        return assemblyOrderRepository.save(mapper.toDomain(dto));
    }

    public AssemblyOrder changeAssemblyOrderStatus(UUID id, AssemblyOrderStatus status) {
        AssemblyOrder order = assemblyOrderRepository.find(id);
        if (order == null) {
            throw new EntityNotFoundException("Error: Assembly order not found.");
        }
        AssemblyOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(status)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        if (status == AssemblyOrderStatus.ASSEMBLED) {
            if (order.getType() == OriginalOrderType.CAR_WITH_CONFIGURATION) {
                Set<UUID> componentIds = order.getRequiredComponentsIds();
                if (componentIds == null || componentIds.isEmpty()) {
                    order.setStatus(AssemblyOrderStatus.FAIL);
                    return assemblyOrderRepository.update(order);
                }
                for (UUID componentId : componentIds) {
                    DetailStock stock = detailStockService.viewDetailStockByDetailId(componentId);
                    if (stock == null || stock.getAvailable() <= 0) {
                        order.setStatus(AssemblyOrderStatus.FAIL);
                        return assemblyOrderRepository.update(order);
                    }
                }
                for (UUID componentId : componentIds) {
                    detailStockService.reserveDetailStock(componentId, 1);
                }
            } else {
                Car car = carRepository.find(order.getCarId());
                if (car == null) {
                    order.setStatus(AssemblyOrderStatus.FAIL);
                    return assemblyOrderRepository.update(order);
                }
            }
        }
        order.setStatus(status);
        assemblyOrderRepository.update(order);
        return order;
    }

    public AssemblyOrder viewAssemblyOrder(UUID id) {
        if (assemblyOrderRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Assembly order not found.");
        }
        return assemblyOrderRepository.find(id);
    }

    public List<AssemblyOrder> viewAssemblyOrderList() {
        return assemblyOrderRepository.findAll();
    }

    public boolean existsBySourceOrderId(UUID id) {
        return assemblyOrderRepository.findBySourceOrderId(id) != null;
    }

    public AssemblyOrder updateAssemblyOrder(AssemblyOrderDTO dto) {
        if (assemblyOrderRepository.find(dto.getId()).getStatus() == AssemblyOrderStatus.CREATED) {
            throw new DomainValidationException("Error: Cannot update created order.");
        }
        return assemblyOrderRepository.update(mapper.toDomain(dto));
    }

    public void deleteAssemblyOrder(UUID id) {
        if (assemblyOrderRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Assembly order not found.");
        }
        if (assemblyOrderRepository.find(id).getStatus() == AssemblyOrderStatus.ASSEMBLED) {
            throw new DomainValidationException("Error: Cannot delete assembled order.");
        }
        assemblyOrderRepository.delete(id);
    }
}
