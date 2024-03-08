package org.example.miniinflearn.api.team.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.example.miniinflearn.api.team.service.TeamService;
import org.example.miniinflearn.api.ApiResponse;
import org.example.miniinflearn.api.team.controller.request.CreateTeamRequest;
import org.example.miniinflearn.domain.team.query.TeamProfileResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/team/list")
    public ApiResponse<List<TeamProfileResponse>> getTeamProfileAll(){
        return ApiResponse.ok(teamService.getTeamProfile());
    }

}
