package org.example.miniinflearn.domain.timecard;

import org.springframework.stereotype.Repository;

@Repository
class TimeCardRepositoryImpl implements TimeCardRepository {

    private final TimeCardJpaRepository jpaRepository;

    TimeCardRepositoryImpl(TimeCardJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Long save(TimeCard timeCard) {
        TimeCard save = jpaRepository.save(timeCard);
        return save.getId();
    }

    @Override
    public TimeCard findById(Long leaveId) {
        return jpaRepository.findById(leaveId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
