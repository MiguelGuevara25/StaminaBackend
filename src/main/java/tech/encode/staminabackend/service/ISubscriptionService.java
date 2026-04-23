package tech.encode.staminabackend.service;

import tech.encode.staminabackend.entity.Subscription;

import java.util.List;

public interface ISubscriptionService {
    List<Subscription> findAll();
    Subscription save(Subscription subscription);
    String checkAccess(String dni);
    void delete(Long id);
}
