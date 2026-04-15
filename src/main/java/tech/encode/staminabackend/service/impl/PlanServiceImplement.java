package tech.encode.staminabackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.encode.staminabackend.entity.Plan;
import tech.encode.staminabackend.repository.IPlanRepository;
import tech.encode.staminabackend.service.IPlanService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImplement implements IPlanService {
    private final IPlanRepository planRepository;

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }
}
