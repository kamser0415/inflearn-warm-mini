package org.example.miniinflearn.api.team.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.employee.controller.request.EmployeeCreateRequest;
import org.example.miniinflearn.api.employee.service.EmployeeService;
import org.example.miniinflearn.api.team.service.TeamService;
import org.example.miniinflearn.api.team.service.response.TeamAddResponse;
import org.example.miniinflearn.api.team.service.response.TeamProfileResponse;
import org.example.miniinflearn.domain.employee.EmployeeJpaRepository;
import org.example.miniinflearn.domain.employee.ROLE;
import org.example.miniinflearn.domain.team.TeamJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamJpaRepository teamJpaRepository;
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Test
    @DisplayName("팀을 등록할 수 있습니다.")
    void addTeam() {
        //팀을 등록하려면
        String name = "팀";
        TeamAddResponse response = teamService.addTeam(name);
        Assertions.assertThat(response.getId()).isEqualTo(1L);
        Assertions.assertThat(response.getName()).isEqualTo(name);
    }

    @BeforeEach
    void setUp() {
        teamJpaRepository.deleteAllInBatch();
        employeeJpaRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("팀을 모두 조회할 수 있습니다.")
    void findAll() {
        //given
        TeamAddResponse teamA = teamService.addTeam("팀A");
        TeamAddResponse teamB = teamService.addTeam("팀B");
        EmployeeCreateRequest request1 = createEmployeeCreateRequest(ROLE.MANAGER, "매니저-둘리", "팀A");
        EmployeeCreateRequest request2 = createEmployeeCreateRequest(ROLE.MEMBER, "직원-또치", "팀A");

        employeeService.joinEmployee(request1);
        employeeService.joinEmployee(request2);

        //when
        List<TeamProfileResponse> results = teamService.getTeamProfile();

        for (TeamProfileResponse result : results) {
            System.out.println("result = " + result);
        }

//        then
//        Assertions.assertThat(results).hasSize(2);
//        Assertions.assertThat(results).extracting("name","manager","memberCount")
//                .containsExactlyInAnyOrder(
//                        Assertions.tuple("팀A","매니저-둘리",2L),
//                        Assertions.tuple("팀B",null,1)
//                );

    }

    private EmployeeCreateRequest createEmployeeCreateRequest(ROLE role, String name, String teamName) {
        return EmployeeCreateRequest
                .builder()
                .role(role)
                .birthday(LocalDate.now())
                .joinDate(LocalDate.now())
                .name(name)
                .teamName(teamName)
                .build();
    }

}
