package org.example.miniinflearn.domain.timecard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
//@ActiveProfiles("test")
class TimeCardJpaRepositoryTest {

    @Autowired
    private TimeCardJpaRepository timeCardJpaRepository;

    @Test
    @DisplayName("출근기록지에 아이디와 출근일이 있으면 ture를 반환한다.")
    void existRecordDay () {
        LocalDate now = toLocalDateBy("2024-04-01");
        // given
        TimeCard entity = TimeCard.builder()
                .employeeId(1L)
                .recordDate(now)
                .build();
        timeCardJpaRepository.save(entity);

        // when
        boolean exists = timeCardJpaRepository.existsByEmployeeIdAndRecordDate(1L, now);

        // then
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("출근기록지에 아이디와 출근일일로 조회시 없는 경우 false 를 반환한다.")
    void existRecordDayFalse () {
        LocalDate now = toLocalDateBy("2024-04-01");

        // when
        boolean exists = timeCardJpaRepository.existsByEmployeeIdAndRecordDate(1L, now);

        // then
        Assertions.assertThat(exists).isFalse();
    }



    private LocalDate toLocalDateBy(String stringLocalDate) {
        return LocalDate.parse(stringLocalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}