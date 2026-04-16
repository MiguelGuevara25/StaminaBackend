package tech.encode.staminabackend.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.encode.staminabackend.dtos.UserDTO;
import tech.encode.staminabackend.entity.User;
import tech.encode.staminabackend.service.IPlanService;
import tech.encode.staminabackend.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
//@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final ModelMapper modelMapper;

    public UserController(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userService.findAll();

        List<UserDTO> usersDto = users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO responseDTO = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userService.save(user);
        UserDTO responseDTO = modelMapper.map(savedUser, UserDTO.class);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User userDetails = modelMapper.map(userDTO, User.class);
        User updatedUser = userService.update(id, userDetails);
        UserDTO responseDTO = modelMapper.map(updatedUser, UserDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
