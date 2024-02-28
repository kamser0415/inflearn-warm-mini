package org.example.miniinflearn.domain.team;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private static long sequence = 0L;
    private Map<Long, Team> persistence = new HashMap<>();

    @Override
    public long save(Team team) {
        team.assignId(++sequence);
        this.persistence.put(team.getId(), team);
        return team.getId();
    }
}
