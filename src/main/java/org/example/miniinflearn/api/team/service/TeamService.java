package org.example.miniinflearn.api.team.service;

import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.example.miniinflearn.domain.team.query.TeamProfileResponse;
import org.example.miniinflearn.domain.team.Team;
import org.example.miniinflearn.domain.team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamAddResponse addTeam(final String name) {
        Team team = new Team(name);
        long id = teamRepository.save(team);
        return new TeamAddResponse(id, name);
    }

    @Transactional(readOnly = true)
    public List<TeamProfileResponse> getTeamProfile() {
        return teamRepository.getTeamProfile();
    }

}
