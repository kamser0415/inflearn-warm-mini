package org.example.miniinflearn.domain.timecard;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniinflearn.domain.employee.Employee;
import java.time.LocalDateTime;
import static org.example.miniinflearn.domain.timecard.WORKSTATIONS.*;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class TimeCard {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime attendLocalDateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    private WORKSTATIONS WORKSTATIONS;

    @Builder
    private TimeCard(final LocalDateTime attendLocalDateTime, final Employee employee, WORKSTATIONS WORKSTATIONS) {
        this.attendLocalDateTime = attendLocalDateTime;
        this.employee = employee;
        this.WORKSTATIONS = WORKSTATIONS;
    }

    public static TimeCard attend(LocalDateTime attendLocalDateTime, Employee employee) {
        return TimeCard.builder()
                .attendLocalDateTime(attendLocalDateTime)
                .employee(employee)
                .WORKSTATIONS(ATTEND)
                .build();
    }

    public void leave() {
        switch (this.WORKSTATIONS) {
            case LEAVE -> throw new IllegalArgumentException("이미 퇴근하셨잖아요");
            case VACATION -> throw new IllegalArgumentException("휴가시잖아요");
            case ATTEND -> this.WORKSTATIONS = LEAVE;
        }
    }
}
