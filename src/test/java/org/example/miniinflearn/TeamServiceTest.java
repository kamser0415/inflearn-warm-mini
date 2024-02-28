package org.example.miniinflearn;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.team.service.TeamService;
import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    @DisplayName("팀을 등록할 수 있습니다.")
    void addTeam() {
        //팀을 등록하려면
        String name = "팀";
        TeamAddResponse response = teamService.addTeam(name);
        Assertions.assertThat(response.getId()).isEqualTo(1L);
        Assertions.assertThat(response.getName()).isEqualTo(name);
    }

}
