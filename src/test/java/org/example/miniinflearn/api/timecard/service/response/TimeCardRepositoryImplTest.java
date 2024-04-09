package org.example.miniinflearn.api.timecard.service.response;

import org.example.miniinflearn.domain.employee.EmployeeRepositoryImpl;
import org.example.miniinflearn.domain.timecard.TimeCard;
import org.example.miniinflearn.domain.timecard.TimeCardRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ActiveProfiles("test")
@DataJpaTest
class TimeCardRepositoryImplTest {

    @Autowired
    TimeCardRepositoryImpl timeCardRepository;
    @Autowired
    EmployeeRepositoryImpl employeeRepository;

    @Test
    @DisplayName("아이디와 오늘 날짜로 출근기록을 조회한다.")
    void findByIdAndAttendAt() {
        //given
        long employeeId = 1L;
        LocalDate workToday = LocalDate.of(2023, 4, 15);
        LocalDateTime attendAt = LocalDateTime.of(workToday, LocalTime.of(8, 0, 0));
        TimeCard timeCard = TimeCard.builder()
                .employeeId(employeeId)
                .attendAt(attendAt)
                .leaveAt(null)
                .build();
        timeCardRepository.save(timeCard);

        //when
        TimeCard todayTimeCard = timeCardRepository.findByIdAndAttendAt(employeeId, workToday)
                .orElse(null);

        //then

    }
}