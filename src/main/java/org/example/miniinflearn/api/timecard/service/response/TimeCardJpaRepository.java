package org.example.miniinflearn.api.timecard.service.response;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeCardJpaRepository extends JpaRepository<TimeCard,Long> {
    Optional<TimeCard> findByIdAndAttendAtStartingWith(Long id, LocalDateTime attendAt);
}
