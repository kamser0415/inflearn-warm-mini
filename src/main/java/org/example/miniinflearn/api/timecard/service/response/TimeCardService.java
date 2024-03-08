package org.example.miniinflearn.api.timecard.service.response;

import org.example.miniinflearn.api.timecard.service.CreateTimeCardWorkRequest;
import org.example.miniinflearn.api.timecard.service.TimeCardWorkResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeRepositoryImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TimeCardService {
    private final EmployeeRepositoryImpl employeeRepository;
    private final TimeCardRepositoryImpl timeCardRepository;

    public TimeCardService(EmployeeRepositoryImpl employeeRepository, TimeCardRepositoryImpl timeCardRepository) {
        this.employeeRepository = employeeRepository;
        this.timeCardRepository = timeCardRepository;
    }

    public TimeCardWorkResponse recordGoWork(CreateTimeCardWorkRequest request) {
        Long employeeId = request.id();

        // 해당 아이디로 직원 정보 가져오기
        Employee findEmployee = employeeRepository.findById(employeeId);

        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul"));
        // 오늘 날짜,직원 아이디로 출퇴근 기록지 정보 가져오기

        // 출근 기록지 정보가 없는 경우
        timeCardRepository.findByIdAndAttendAt(employeeId, zonedDateTime.toLocalDate());
            // 출퇴근 기록지 엔티티 만들기
            // 출퇴근 기록지 엔티티가 출근 기록
                // 세부 로직은 출근 기록에서 처리

        // 출근 기록지 정보가 있는 경우
            // 출퇴근 기록지 엔티티가 출근 기록
                // 세부 로직은 출근 기록에서 처리

        throw new IllegalArgumentException();
    }
}
