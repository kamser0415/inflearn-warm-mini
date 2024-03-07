package org.example.miniinflearn.domain.timecard;

public interface TimeCardRepository {
    Long save(TimeCard timeCard);

    TimeCard findById(Long leaveId);
}
