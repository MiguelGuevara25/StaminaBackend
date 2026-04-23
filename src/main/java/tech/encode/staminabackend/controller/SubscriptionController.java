package tech.encode.staminabackend.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.encode.staminabackend.dtos.SubscriptionDTO;
import tech.encode.staminabackend.entity.Subscription;
import tech.encode.staminabackend.service.IPlanService;
import tech.encode.staminabackend.service.ISubscriptionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubscriptionController {
    private final ISubscriptionService subscriptionService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAll() {
        List<Subscription> subscriptions = subscriptionService.findAll();

        List<SubscriptionDTO> subscriptionsDto = subscriptions.stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(subscriptionsDto);
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> save(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
        Subscription savedSubscription = subscriptionService.save(subscription);
        SubscriptionDTO responseDTO = modelMapper.map(savedSubscription, SubscriptionDTO.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/check/{dni}")
    public ResponseEntity<String> checkAccess(@PathVariable String dni) {
        String message = subscriptionService.checkAccess(dni);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
