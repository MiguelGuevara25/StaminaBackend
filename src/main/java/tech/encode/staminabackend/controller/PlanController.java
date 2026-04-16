package tech.encode.staminabackend.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.encode.staminabackend.dtos.PlanDTO;
import tech.encode.staminabackend.entity.Plan;
import tech.encode.staminabackend.service.IPlanService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin("*")
//@RequiredArgsConstructor
public class PlanController {
    private final IPlanService planService;
    private final ModelMapper modelMapper;

    public PlanController(IPlanService planService, ModelMapper modelMapper) {
        this.planService = planService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAll() {
        List<Plan> plans = planService.findAll();

        List<PlanDTO> plansDto = plans.stream()
                .map(plan -> modelMapper.map(plan, PlanDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(plansDto);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> save(@RequestBody PlanDTO planDTO) {
        Plan plan = modelMapper.map(planDTO, Plan.class);
        Plan savedPlan = planService.save(plan);
        PlanDTO responseDTO = modelMapper.map(savedPlan, PlanDTO.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
