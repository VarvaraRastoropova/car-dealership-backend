package ru.rastoropova.persistence_layer.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.rastoropova.persistence_layer.entities.CarEntity;
import ru.rastoropova.service_layer.cars.CarFilter;

@Component
public class CarSpecifications {
    public Specification<CarEntity> withFilter(CarFilter filter) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();
            if (filter.getPrice() != 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("model").get("basePrice"), filter.getPrice()));
            }
            if (filter.getBrand() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("model").get("brand"), filter.getBrand()));
            }
            if (filter.getBodyId() != null) {
                predicate =  criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("bodyDetail").get("id"), filter.getBodyId()));
            }
            if (filter.getEngineId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("engineDetail").get("id"), filter.getEngineId()));
            }
            if (filter.getGearboxId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("gearboxDetail").get("id"), filter.getGearboxId()));
            }
            if (filter.getInteriorId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("interiorDetail").get("id"), filter.getInteriorId()));
            }
            if (filter.getSteeringWheelId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("steeringWheelDetail").get("id"), filter.getSteeringWheelId()));
            }
            if (filter.getWheelsId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("configuration").get("wheelsDetail").get("id"), filter.getWheelsId()));
            }
            return predicate;
        };
    }
}
