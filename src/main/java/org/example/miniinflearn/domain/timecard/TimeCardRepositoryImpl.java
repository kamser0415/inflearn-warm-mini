package org.example.miniinflearn.domain.timecard;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional
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

    public boolean existTimeCardToDay(long employeeId, LocalDate today) {
        return timeCardJpaRepository.existsByEmployeeIdAndRecordDate(employeeId, today);
    }
}
