package org.example.miniinflearn.domain.timecard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCardJpaRepository extends JpaRepository<TimeCard,Long> {
}
