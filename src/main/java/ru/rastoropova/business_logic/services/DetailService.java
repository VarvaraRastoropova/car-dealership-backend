package ru.rastoropova.business_logic.services;

import ru.rastoropova.business_logic.details.*;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;
import ru.rastoropova.business_logic.exceptions.EntityNotFoundException;
import ru.rastoropova.business_logic.repositories.*;
import ru.rastoropova.business_logic.users.Role;
import ru.rastoropova.business_logic.users.User;

import java.util.List;

public class DetailService {
    private BodyDetailRepository bodyDetailRepository;
    private EngineDetailRepository engineDetailRepository;
    private GearboxDetailRepository gearboxDetailRepository;
    private InteriorDetailRepository interiorDetailRepository;
    private SteeringWheelDetailRepository steeringWheelDetailRepository;
    private WheelsDetailRepository wheelsDetailRepository;

    public DetailService(BodyDetailRepository bodyDetailRepository, EngineDetailRepository engineDetailRepository,
                         GearboxDetailRepository gearboxDetailRepository, InteriorDetailRepository interiorDetailRepository,
                         SteeringWheelDetailRepository steeringWheelDetailRepository, WheelsDetailRepository wheelsDetailRepository) {
        this.bodyDetailRepository = bodyDetailRepository;
        this.engineDetailRepository = engineDetailRepository;
        this.gearboxDetailRepository = gearboxDetailRepository;
        this.interiorDetailRepository = interiorDetailRepository;
        this.steeringWheelDetailRepository = steeringWheelDetailRepository;
        this.wheelsDetailRepository = wheelsDetailRepository;
    }

    public BodyDetail viewBodyDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        BodyDetail detail = bodyDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Body not found.");
        }
        return bodyDetailRepository.find(id);
    }

    public EngineDetail viewEngineDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        EngineDetail detail = engineDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Engine not found.");
        }
        return engineDetailRepository.find(id);
    }

    public GearboxDetail viewGearboxDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        GearboxDetail detail = gearboxDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Gearbox not found.");
        }
        return gearboxDetailRepository.find(id);
    }

    public InteriorDetail viewInteriorDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        InteriorDetail detail = interiorDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Interior not found.");
        }
        return interiorDetailRepository.find(id);
    }

    public SteeringWheelDetail viewSteeringWheelDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        SteeringWheelDetail detail = steeringWheelDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Steering Wheel not found.");
        }
        return steeringWheelDetailRepository.find(id);
    }

    public WheelsDetail viewWheelsDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        WheelsDetail detail = wheelsDetailRepository.find(id);
        if (detail == null) {
            throw new EntityNotFoundException("Error: Wheels not found.");
        }
        return wheelsDetailRepository.find(id);
    }

    public List<BodyDetail> viewBodyDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return bodyDetailRepository.findAll();
    }

    public List<EngineDetail> viewEngineDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return engineDetailRepository.findAll();
    }

    public List<GearboxDetail> viewGearboxDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return gearboxDetailRepository.findAll();
    }

    public List<InteriorDetail> viewInteriorDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return interiorDetailRepository.findAll();
    }

    public List<SteeringWheelDetail> viewSteeringWheelDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return steeringWheelDetailRepository.findAll();
    }

    public List<WheelsDetail> viewWheelsDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return wheelsDetailRepository.findAll();
    }

    public void addBodyDetailToSystem(User user, BodyDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (bodyDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Body detail already exists.");
        }
        bodyDetailRepository.save(detail);
    }

    public void addEngineDetailToSystem(User user, EngineDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (engineDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Engine detail already exists.");
        }
        engineDetailRepository.save(detail);
    }

    public void addGearboxDetailToSystem(User user, GearboxDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (gearboxDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Gearbox detail already exists.");
        }
        gearboxDetailRepository.save(detail);
    }

        public void addInteriorDetailToSystem(User user, InteriorDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (interiorDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Interior detail already exists.");
        }
        interiorDetailRepository.save(detail);
    }

    public void addSteeringWheelDetailToSystem(User user, SteeringWheelDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (steeringWheelDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Steering wheel detail already exists.");
        }
        steeringWheelDetailRepository.save(detail);
    }

    public void addWheelsDetailToSystem(User user, WheelsDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (wheelsDetailRepository.find(detail.getId()) != null) {
            throw new DomainValidationException("Error: Wheels detail already exists.");
        }
        wheelsDetailRepository.save(detail);
    }

    public void deleteBodyDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (bodyDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Body detail not found.");
        }
        bodyDetailRepository.delete(id);
    }

    public void deleteEngineDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (engineDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Engine detail not found.");
        }
        engineDetailRepository.delete(id);
    }

    public void deleteGearboxDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (gearboxDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Gearbox detail not found.");
        }
        gearboxDetailRepository.delete(id);
    }

    public void deleteInteriorDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (interiorDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Interior detail not found.");
        }
        interiorDetailRepository.delete(id);
    }

    public void deleteSteeringWheelDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (steeringWheelDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Steering wheel detail not found.");
        }
        steeringWheelDetailRepository.delete(id);
    }

    public void deleteWheelsDetailFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (wheelsDetailRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Wheels detail not found.");
        }
        wheelsDetailRepository.delete(id);
    }
}
