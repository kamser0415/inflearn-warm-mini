package org.example.miniinflearn.domain.timecard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {
    
    @Test
    @DisplayName("아이디와 오늘 날짜를 입력하면 출퇴근이 기록이된다.")
    void recordInit() {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 4, 15, 10, 10, 10);
        Long employeeId = 1L;

        // when
        TimeCard timeCard = TimeCard.recordWork(employeeId, now);

        // then
        Assertions.assertThat(timeCard.getEmployeeId()).isEqualTo(employeeId);
        Assertions.assertThat(timeCard.getRecordDate()).isEqualTo(now.toLocalDate());
        Assertions.assertThat(timeCard.getAttendAt()).isEqualTo(now);
        Assertions.assertThat(timeCard.getLeaveAt()).isEqualTo(now);
    }

}