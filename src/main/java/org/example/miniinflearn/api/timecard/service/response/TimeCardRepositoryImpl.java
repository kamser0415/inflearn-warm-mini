package org.example.miniinflearn.api.timecard.service.response;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class TimeCardRepositoryImpl implements TimeCardRepository {

    private final TimeCardJpaRepository timeCardJpaRepository;

    public TimeCardRepositoryImpl(TimeCardJpaRepository timeCardJpaRepository) {
        this.timeCardJpaRepository = timeCardJpaRepository;
    }

    public Optional<TimeCard> findByIdAndAttendAt(long id, LocalDate workDay) {
        return this.timeCardJpaRepository.findByIdAndAttendAtStartingWith(id, workDay.atStartOfDay());
    }

    @Override
    public long save(TimeCard timeCard) {
        TimeCard save = this.timeCardJpaRepository.save(timeCard);
        return save.getId();
    }
}
