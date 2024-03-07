package org.example.miniinflearn.api.team.service;

import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.example.miniinflearn.api.team.service.response.TeamProfileResponse;
import org.example.miniinflearn.domain.team.Team;
import org.example.miniinflearn.domain.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamAddResponse addTeam(String name) {
        Team team = new Team(name);
        long id = teamRepository.save(team);
        return new TeamAddResponse(id, name);
    }
    public List<TeamProfileResponse> getTeamProfile() {
        return teamRepository.getTeamProfile();
    }

}
