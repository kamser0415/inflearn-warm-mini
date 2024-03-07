package org.example.miniinflearn.domain.team;

import org.example.miniinflearn.api.team.service.response.TeamProfileResponse;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    long save(Team team);

    Optional<Team> findByName(String name);

    List<TeamProfileResponse> getTeamProfile();
}
