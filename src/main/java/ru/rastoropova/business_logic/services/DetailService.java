package ru.rastoropova.business_logic.services;

import ru.rastoropova.business_logic.details.*;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;
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
        return bodyDetailRepository.find(id);
    }

    public EngineDetail viewEngineDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return engineDetailRepository.find(id);
    }

    public GearboxDetail viewGearboxDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return gearboxDetailRepository.find(id);
    }

    public InteriorDetail viewInteriorDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return interiorDetailRepository.find(id);
    }

    public SteeringWheelDetail viewSteeringWheelDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return steeringWheelDetailRepository.find(id);
    }

    public WheelsDetail viewWheelsDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
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
        bodyDetailRepository.save(detail);
    }

    public void addEngineDetailToSystem(User user, EngineDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        engineDetailRepository.save(detail);
    }

    public void addGearboxDetailToSystem(User user, GearboxDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        gearboxDetailRepository.save(detail);
    }

        public void addInteriorDetailToSystem(User user, InteriorDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        interiorDetailRepository.save(detail);
    }

    public void addSteeringWheelDetailToSystem(User user, SteeringWheelDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        steeringWheelDetailRepository.save(detail);
    }

    public void addWheelsDetailToSystem(User user, WheelsDetail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        wheelsDetailRepository.save(detail);
    }

    public void deleteBodyDetailFromSystem(User user, BodyDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        bodyDetailRepository.delete(detail);
    }

    public void deleteEngineDetailFromSystem(User user, EngineDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        engineDetailRepository.delete(detail);
    }

    public void deleteGearboxDetailFromSystem(User user, GearboxDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        gearboxDetailRepository.delete(detail);
    }

    public void deleteInteriorDetailFromSystem(User user, InteriorDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        interiorDetailRepository.delete(detail);
    }

    public void deleteSteeringWheelDetailFromSystem(User user, SteeringWheelDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        steeringWheelDetailRepository.delete(detail);
    }

    public void deleteWheelsDetailFromSystem(User user, WheelsDetail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        wheelsDetailRepository.delete(detail);
    }
}
