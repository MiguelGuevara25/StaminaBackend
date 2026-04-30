package tech.encode.staminabackend.service;

import tech.encode.staminabackend.entity.Attendance;

import java.util.List;

public interface IAttendanceService {
    List<Attendance> findAll();
}
