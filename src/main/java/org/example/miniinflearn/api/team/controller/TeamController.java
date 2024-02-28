package org.example.miniinflearn.api.team.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.example.miniinflearn.api.team.service.TeamService;
import org.example.miniinflearn.api.ApiResponse;
import org.example.miniinflearn.api.team.controller.request.CreateTeamRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TeamController {

    private final TeamService teamService;
    @PostMapping("/team")
    public ApiResponse<TeamAddResponse> joinTeam(@RequestBody @Valid CreateTeamRequest request) {
        TeamAddResponse teamAddResponse = teamService.addTeam(request.getName());
        return ApiResponse.ok(teamAddResponse);
    }

}
