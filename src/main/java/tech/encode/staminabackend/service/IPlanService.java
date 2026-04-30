package tech.encode.staminabackend.service;

import tech.encode.staminabackend.entity.Plan;

import java.util.List;

public interface IPlanService {
    List<Plan> findAll();
    List<Plan> findAllIncluding();
    Plan save(Plan plan);
    Plan update(Long id, Plan planDetails);
    void delete(Long id);
    void desactivate(Long id);
}
