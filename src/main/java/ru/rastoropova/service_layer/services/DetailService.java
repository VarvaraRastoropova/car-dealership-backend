package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.details.*;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.*;
import ru.rastoropova.service_layer.users.Role;
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

    public BodyDetail viewBodyDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        BodyDetail detail = bodyDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Body not found.");
        }
        return bodyDetailRepository.find(id);
    }

    public EngineDetail viewEngineDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        EngineDetail detail = engineDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Engine not found.");
        }
        return engineDetailRepository.find(id);
    }

    public GearboxDetail viewGearboxDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        GearboxDetail detail = gearboxDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Gearbox not found.");
        }
        return gearboxDetailRepository.find(id);
    }

    public InteriorDetail viewInteriorDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        InteriorDetail detail = interiorDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Interior not found.");
        }
        return interiorDetailRepository.find(id);
    }

    public SteeringWheelDetail viewSteeringWheelDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        SteeringWheelDetail detail = steeringWheelDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Steering Wheel not found.");
        }
        return steeringWheelDetailRepository.find(id);
    }

    public WheelsDetail viewWheelsDetail(Role userRole, UUID id) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        WheelsDetail detail = wheelsDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Wheels not found.");
        }
        return wheelsDetailRepository.find(id);
    }

    public List<BodyDetail> viewBodyDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return bodyDetailRepository.findAll();
    }

    public List<EngineDetail> viewEngineDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return engineDetailRepository.findAll();
    }

    public List<GearboxDetail> viewGearboxDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return gearboxDetailRepository.findAll();
    }

    public List<InteriorDetail> viewInteriorDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return interiorDetailRepository.findAll();
    }

    public List<SteeringWheelDetail> viewSteeringWheelDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return steeringWheelDetailRepository.findAll();
    }

    public List<WheelsDetail> viewWheelsDetailList(Role userRole) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole!= Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return wheelsDetailRepository.findAll();
    }

    public void addBodyDetailToSystem(Role userRole, BodyDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && bodyDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Body detail already exists.");
        }
        bodyDetailRepository.save(detail);
    }

    public void addEngineDetailToSystem(Role userRole, EngineDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && engineDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Engine detail already exists.");
        }
        engineDetailRepository.save(detail);
    }

    public void addGearboxDetailToSystem(Role userRole, GearboxDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && gearboxDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Gearbox detail already exists.");
        }
        gearboxDetailRepository.save(detail);
    }

        public void addInteriorDetailToSystem(Role userRole, InteriorDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && interiorDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Interior detail already exists.");
        }
        interiorDetailRepository.save(detail);
    }

    public void addSteeringWheelDetailToSystem(Role userRole, SteeringWheelDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && steeringWheelDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Steering wheel detail already exists.");
        }
        steeringWheelDetailRepository.save(detail);
    }

    public void addWheelsDetailToSystem(Role userRole, WheelsDetail detail) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (detail.getId() != null && wheelsDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Wheels detail already exists.");
        }
        wheelsDetailRepository.save(detail);
    }

    public void deleteBodyDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (bodyDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Body detail not found.");
        }
        bodyDetailRepository.delete(id);
    }

    public void deleteEngineDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (engineDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Engine detail not found.");
        }
        engineDetailRepository.delete(id);
    }

    public void deleteGearboxDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (gearboxDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Gearbox detail not found.");
        }
        gearboxDetailRepository.delete(id);
    }

    public void deleteInteriorDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (interiorDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Interior detail not found.");
        }
        interiorDetailRepository.delete(id);
    }

    public void deleteSteeringWheelDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (steeringWheelDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Steering wheel detail not found.");
        }
        steeringWheelDetailRepository.delete(id);
    }

    public void deleteWheelsDetailFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (wheelsDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Wheels detail not found.");
        }
        wheelsDetailRepository.delete(id);
    }
}
