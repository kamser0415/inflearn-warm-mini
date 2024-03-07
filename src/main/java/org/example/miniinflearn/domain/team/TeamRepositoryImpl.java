package org.example.miniinflearn.domain.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.miniinflearn.api.team.service.response.TeamProfileResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final TeamJpaRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public TeamRepositoryImpl(TeamJpaRepository jpaRepository, JPAQueryFactory jpaQueryFactory) {
        this.jpaRepository = jpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public long save(Team team) {
        Team save = jpaRepository.save(team);
        return save.getId();
    }

    @Override
    public Optional<Team> findByName(String name) {
        return jpaRepository.findByName(name);
    }

    @Override
    public List<TeamProfileResponse> getTeamProfile() {
        return jpaRepository.getTeamProfile();
    }
}
