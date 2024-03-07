package org.example.miniinflearn.api.timecard.service;

import org.example.miniinflearn.api.timecard.controller.request.CreateAttendRequest;
import org.example.miniinflearn.api.timecard.service.response.AttendResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeRepository;
import org.example.miniinflearn.domain.timecard.TimeCard;
import org.example.miniinflearn.domain.timecard.TimeCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TimeCardService {
    private final EmployeeRepository employeeService;
    private final TimeCardRepository timeCardRepository;

    public TimeCardService(EmployeeRepository employeeService, TimeCardRepository timeCardRepository) {
        this.employeeService = employeeService;
        this.timeCardRepository = timeCardRepository;
    }

    @Transactional
    public AttendResponse recordAttendance(CreateAttendRequest request) {
        Long attendId = request.id();
        // 해당 아이디로 등록된 직원이 있는지 확인한다.
        Employee employee = employeeService.findById(attendId);
        // 기록한다.
        LocalDateTime attendLocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        TimeCard timeCard = TimeCard.attend(attendLocalDateTime, employee);
        Long timeCardId = timeCardRepository.save(timeCard);
        return new AttendResponse(timeCardId, employee.getName());
    }
    @Transactional
    public AttendResponse recordLeave(CreateAttendRequest request) {
        Long leaveId = request.id();
        // 해당 아이디로 등록된 직원이 있는지 확인한다.
        TimeCard employeeTimeCard = timeCardRepository.findById(leaveId);
        // 출퇴근 기록지를 가져온다.
        employeeTimeCard.leave();
        return new AttendResponse(leaveId, employeeTimeCard.getEmployee().getName());
    }


}
