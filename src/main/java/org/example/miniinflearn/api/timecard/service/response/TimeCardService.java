package org.example.miniinflearn.api.timecard.service.response;

import org.example.miniinflearn.api.timecard.service.CreateTimeCardWorkRequest;
import org.example.miniinflearn.api.timecard.service.TimeCardWorkResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeRepositoryImpl;
import org.example.miniinflearn.domain.timecard.TimeCard;
import org.example.miniinflearn.domain.timecard.TimeCardRepositoryImpl;
import org.springframework.stereotype.Service;

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

        // 해당 아이디로 직원 정보 가져오기
        Long employeeId = employeeRepository.findById(request.id())
                                            .getId();

        // 별도 빈으로 대체
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul"));
        // 오늘 날짜,직원 아이디로 출퇴근 기록지 정보 가져오기

        // 오늘 출근 기록지에 저장한 내용이 있는지 확인한다.
        boolean exist = timeCardRepository.existTimeCardToDay(employeeId, zonedDateTime.toLocalDate());

        // 존재하는 경우 반환
        if(!exist) {
            return new TimeCardWorkResponse(employeeId);
        }

        long saveId = timeCardRepository.save(TimeCard.recordWork(employeeId, zonedDateTime.toLocalDateTime()));
        return new TimeCardWorkResponse(saveId);
    }

    public TimeCardWorkResponse recordleave(CreateTimeCardWorkRequest request) {
        // 존재하는 회원인지 조회하기

        // 존재하지 않을 경우 예외 발생

        // 오늘 날짜로 출퇸근 기록지가 있는지 조회하기
        // 있는 경우 퇴근 기록
        // 없는 경우
        // 어제 날짜로 출근 기록지가 있는지 조회
        // 없는경우
        // 오늘 날짜로 퇴근기록

        //
        return null;
    }

    //퇴근기능

}
