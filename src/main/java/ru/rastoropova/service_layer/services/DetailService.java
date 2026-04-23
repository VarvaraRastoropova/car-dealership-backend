package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.details.*;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.*;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DetailService {
    private BodyDetailRepository bodyDetailRepository;
    private EngineDetailRepository engineDetailRepository;
    private GearboxDetailRepository gearboxDetailRepository;
    private InteriorDetailRepository interiorDetailRepository;
    private SteeringWheelDetailRepository steeringWheelDetailRepository;
    private WheelsDetailRepository wheelsDetailRepository;

    public BodyDetail viewBodyDetail(UUID id) {
        BodyDetail detail = bodyDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Body not found.");
        }
        return bodyDetailRepository.find(id);
    }

    public EngineDetail viewEngineDetail(UUID id) {
        EngineDetail detail = engineDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Engine not found.");
        }
        return engineDetailRepository.find(id);
    }

    public GearboxDetail viewGearboxDetail(UUID id) {
        GearboxDetail detail = gearboxDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Gearbox not found.");
        }
        return gearboxDetailRepository.find(id);
    }

    public InteriorDetail viewInteriorDetail(UUID id) {
        InteriorDetail detail = interiorDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Interior not found.");
        }
        return interiorDetailRepository.find(id);
    }

    public SteeringWheelDetail viewSteeringWheelDetail(UUID id) {
        SteeringWheelDetail detail = steeringWheelDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Steering Wheel not found.");
        }
        return steeringWheelDetailRepository.find(id);
    }

    public WheelsDetail viewWheelsDetail(UUID id) {
        WheelsDetail detail = wheelsDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Wheels not found.");
        }
        return wheelsDetailRepository.find(id);
    }

    public List<BodyDetail> viewBodyDetailList() {
        return bodyDetailRepository.findAll();
    }

    public List<EngineDetail> viewEngineDetailList() {
        return engineDetailRepository.findAll();
    }

    public List<GearboxDetail> viewGearboxDetailList() {
        return gearboxDetailRepository.findAll();
    }

    public List<InteriorDetail> viewInteriorDetailList() {
        return interiorDetailRepository.findAll();
    }

    public List<SteeringWheelDetail> viewSteeringWheelDetailList() {
        return steeringWheelDetailRepository.findAll();
    }

    public List<WheelsDetail> viewWheelsDetailList() {
        return wheelsDetailRepository.findAll();
    }

    public BodyDetail addBodyDetailToSystem(BodyDetail detail) {
        if (detail.getId() != null && bodyDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Body detail already exists.");
        }
        return bodyDetailRepository.save(detail);
    }

    public EngineDetail addEngineDetailToSystem(EngineDetail detail) {
        if (detail.getId() != null && engineDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Engine detail already exists.");
        }
        return engineDetailRepository.save(detail);
    }

    public GearboxDetail addGearboxDetailToSystem(GearboxDetail detail) {
        if (detail.getId() != null && gearboxDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Gearbox detail already exists.");
        }
        return gearboxDetailRepository.save(detail);
    }

        public InteriorDetail addInteriorDetailToSystem(InteriorDetail detail) {
        if (detail.getId() != null && interiorDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Interior detail already exists.");
        }
            return interiorDetailRepository.save(detail);
    }

    public SteeringWheelDetail addSteeringWheelDetailToSystem(SteeringWheelDetail detail) {
        if (detail.getId() != null && steeringWheelDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Steering wheel detail already exists.");
        }
        return steeringWheelDetailRepository.save(detail);
    }

    public WheelsDetail addWheelsDetailToSystem(WheelsDetail detail) {
        if (detail.getId() != null && wheelsDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Wheels detail already exists.");
        }
        return wheelsDetailRepository.save(detail);
    }

    public void deleteBodyDetailFromSystem(UUID id) {
        if (bodyDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Body detail not found.");
        }
        bodyDetailRepository.delete(id);
    }

    public void deleteEngineDetailFromSystem(UUID id) {
        if (engineDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Engine detail not found.");
        }
        engineDetailRepository.delete(id);
    }

    public void deleteGearboxDetailFromSystem(UUID id) {
        if (gearboxDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Gearbox detail not found.");
        }
        gearboxDetailRepository.delete(id);
    }

    public void deleteInteriorDetailFromSystem(UUID id) {
        if (interiorDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Interior detail not found.");
        }
        interiorDetailRepository.delete(id);
    }

    public void deleteSteeringWheelDetailFromSystem(UUID id) {
        if (steeringWheelDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Steering wheel detail not found.");
        }
        steeringWheelDetailRepository.delete(id);
    }

    public void deleteWheelsDetailFromSystem(UUID id) {
        if (wheelsDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Wheels detail not found.");
        }
        wheelsDetailRepository.delete(id);
    }
}
