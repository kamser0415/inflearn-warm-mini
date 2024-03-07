package org.example.miniinflearn.api.timecard.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.domain.timecard.TimeCard;
import org.example.miniinflearn.domain.timecard.WORKSTATIONS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeCardTest {

    @Test
    @DisplayName("퇴근한 직원이 출근하려하면 예외가 발생한다.")
    void notReturn() {
        TimeCard workstations = TimeCard.builder()
                .WORKSTATIONS(WORKSTATIONS.LEAVE)
                .build();
        Assertions.assertThatThrownBy(workstations::leave)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("출근한 직원은 퇴근이 가능하다.")
    void okGoHome() {
        TimeCard workstations = TimeCard.builder()
                .WORKSTATIONS(WORKSTATIONS.ATTEND)
                .build();
        workstations.leave();

        Assertions.assertThat(workstations.getWORKSTATIONS()).isEqualTo(WORKSTATIONS.LEAVE);
    }

    @Test
    @DisplayName("휴가인 직원은 출근이 퇴근이 불가능하다.")
    void vocationNotHome() {
        TimeCard workstations = TimeCard.builder()
                .WORKSTATIONS(WORKSTATIONS.VACATION)
                .build();
        Assertions.assertThatThrownBy(workstations::leave)
                .isInstanceOf(IllegalArgumentException.class);
    }
}