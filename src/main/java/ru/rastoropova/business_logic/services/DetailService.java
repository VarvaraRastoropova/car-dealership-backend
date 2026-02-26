package ru.rastoropova.business_logic.services;

import ru.rastoropova.business_logic.details.Detail;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;
import ru.rastoropova.business_logic.repositories.DetailRepository;
import ru.rastoropova.business_logic.users.Role;
import ru.rastoropova.business_logic.users.User;

import java.util.List;

public class DetailService {
    private DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Detail viewDetail(User user, int id) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return detailRepository.find(id);
    }

    public List<Detail> viewDetailList(User user) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return detailRepository.findAll();
    }

    public void addDetailToSystem(User user, Detail detail) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        detailRepository.save(detail);
    }

    public void deleteDetailFromSystem(User user, Detail detail) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        detailRepository.delete(detail);
    }
}
