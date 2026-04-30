package tech.encode.staminabackend.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.encode.staminabackend.dtos.AttendanceDTO;
import tech.encode.staminabackend.entity.Attendance;
import tech.encode.staminabackend.service.IAttendanceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendances")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AttendanceController {
    private final IAttendanceService attendanceService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAll() {
        List<Attendance> attendances = attendanceService.findAll();
        List<AttendanceDTO> attendancesDto = attendances.stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(attendancesDto);
    }
}
