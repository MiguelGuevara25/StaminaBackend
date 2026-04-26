package tech.encode.staminabackend.service;

import tech.encode.staminabackend.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    List<User> findAllIncluding();
    User save(User user);
    User findByDni(String dni);
    User findById(Long id);
    User update(Long id, User userDetails);
    void delete(Long id);
    void desactivate(Long id);
}
