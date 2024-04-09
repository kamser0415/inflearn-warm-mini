package org.example.miniinflearn.domain.timecard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long employeeId;
    /**
     * recordDate를 추가한 이유:
     * 해당 날짜의 출퇴근 기록을 검색하기 위한 필드
     * 근무일에 대한 정보를 명확하게 나타내기 위해서 별도로 사용
     */
    private LocalDate recordDate;
    private LocalDateTime attendAt; // 출근시간
    private LocalDateTime leaveAt; // 퇴근시간

    @Builder
    public TimeCard(long employeeId, LocalDate recordDate, LocalDateTime attendAt, LocalDateTime leaveAt) {
        this.employeeId = employeeId;
        this.recordDate = recordDate;
        this.attendAt = attendAt;
        this.leaveAt = leaveAt;
    }

    public static TimeCard recordWork(Long employeeId, LocalDateTime localDate) {
        return TimeCard.builder()
                .employeeId(employeeId)
                .recordDate(localDate.toLocalDate())
                .attendAt(localDate)
                .leaveAt(localDate)
                .build();
    }

    public long getId() {
        return id;
    }
}
