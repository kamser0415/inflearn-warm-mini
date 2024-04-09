package org.example.miniinflearn.domain.timecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeCardJpaRepository extends JpaRepository<TimeCard,Long> {
    Optional<TimeCard> findByIdAndAttendAtStartingWith(Long id, LocalDateTime attendAt);

    boolean existsByEmployeeIdAndRecordDate(Long employeeId, LocalDate recordDay);
}
