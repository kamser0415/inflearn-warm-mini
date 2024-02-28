package org.example.miniinflearn.api.team.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTeamRequest {

    @NotBlank(message = "팀명은 공백이나 null이 들어올 수 없습니다.")
    private String name;

    public CreateTeamRequest(String name) {
        this.name = name;
    }
}
