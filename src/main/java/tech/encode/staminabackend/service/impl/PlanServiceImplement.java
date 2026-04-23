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

    @Override
    public Plan update(Long id, Plan planDetails) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el plan con ID: " + id));

        plan.setName(planDetails.getName());
        plan.setDurationDays(planDetails.getDurationDays());
        plan.setPrice(planDetails.getPrice());
        plan.setDescription(planDetails.getDescription());

        return planRepository.save(plan);
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }
}
