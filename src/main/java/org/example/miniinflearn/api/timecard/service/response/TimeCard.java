package org.example.miniinflearn.api.timecard.service.response;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long employeeId;
    private LocalDateTime attendAt;
    private LocalDateTime leaveAt;

    @Builder
    public TimeCard(long employeeId, LocalDateTime attendAt, LocalDateTime leaveAt) {
        this.employeeId = employeeId;
        this.attendAt = attendAt;
        this.leaveAt = leaveAt;
    }

    public long getId() {
        return id;
    }
}
