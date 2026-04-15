package tech.encode.staminabackend.service;

import tech.encode.staminabackend.entity.Plan;
import java.util.List;

public interface IPlanService {
    List<Plan> findAll();
    Plan save(Plan plan);
}
