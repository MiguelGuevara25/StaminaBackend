package tech.encode.staminabackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.encode.staminabackend.entity.Attendance;
import tech.encode.staminabackend.repository.IAttendanceRepository;
import tech.encode.staminabackend.service.IAttendanceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImplement implements IAttendanceService {
    private final IAttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAllByOrderByEntryDateDesc();
    }
}
