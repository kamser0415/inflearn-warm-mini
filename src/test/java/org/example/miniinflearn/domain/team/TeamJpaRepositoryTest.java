package org.example.miniinflearn.domain.team;

import org.example.miniinflearn.domain.team.query.TeamProfileResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeJpaRepository;
import org.example.miniinflearn.domain.employee.ROLE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
class TeamJpaRepositoryTest {

    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Test
    @DisplayName("등록된 팀에 매니저 직원이 있을 경우 팀명,매니저 이름,직원 수를 조회할 수 있다.")
    void searchTeamProfile() {
        //given
        Team teamA = teamJpaRepository.save(new Team("팀A"));

        Employee managerEmployee = createEmployee("매니저", ROLE.MANAGER);
        managerEmployee.joinTeam(teamA.getId());
        Employee memberEmployee = createEmployee("직원", ROLE.MEMBER);
        memberEmployee.joinTeam(teamA.getId());

        employeeJpaRepository.saveAll(List.of(managerEmployee,memberEmployee));

        //when
        List<TeamProfileResponse> teamProfile = teamJpaRepository.getTeamProfile();

        //then
        assertThat(teamProfile).hasSize(1);
        assertThat(teamProfile).extracting("name","manager","memberCount")
                .containsExactlyInAnyOrder(
                        tuple("팀A","매니저",2L)
                );
    }

    @Test
    @DisplayName("등록된 팀에 매니저 직원이 두명 이상 있을 경우 팀명,모든 매니저 이름(구분자 ,),직원 수를 조회할 수 있다.")
    void searchTeamProfileWithTwoManager() {
        //given
        Team teamA = teamJpaRepository.save(new Team("팀A"));

        Employee managerEmployee = createEmployee("매니저-A", ROLE.MANAGER);
        managerEmployee.joinTeam(teamA.getId());

        Employee managerEmployee2 = createEmployee("매니저-B", ROLE.MANAGER);
        managerEmployee2.joinTeam(teamA.getId());

        employeeJpaRepository.saveAll(List.of(managerEmployee,managerEmployee2));

        //when
        List<TeamProfileResponse> teamProfile = teamJpaRepository.getTeamProfile();

        for (TeamProfileResponse teamProfileResponse : teamProfile) {
            System.out.println("teamProfileResponse = " + teamProfileResponse);
        }

        //then
        assertThat(teamProfile).hasSize(1);
        assertThat(teamProfile).extracting("name","manager","memberCount")
                .containsExactlyInAnyOrder(
                        tuple("팀A","매니저-A,매니저-B",2L)
                );
    }

    @Test
    @DisplayName("등록된 팀에 매니저 직원이 없을 경우 팀명,직원 수를 조회할 수 있고, 매니저 이름은 null이다.")
    void searchTeamProfileWithOutManager() {
        //given
        Team teamA = teamJpaRepository.save(new Team("팀A"));

        Employee managerEmployee = createEmployee("직원2", ROLE.MEMBER);
        managerEmployee.joinTeam(teamA.getId());
        Employee memberEmployee = createEmployee("직원", ROLE.MEMBER);
        memberEmployee.joinTeam(teamA.getId());

        employeeJpaRepository.saveAll(List.of(managerEmployee,memberEmployee));

        //when
        List<TeamProfileResponse> teamProfile = teamJpaRepository.getTeamProfile();

        //then
        assertThat(teamProfile).hasSize(1);
        assertThat(teamProfile).extracting("name","manager","memberCount")
                .containsExactlyInAnyOrder(
                        tuple("팀A",null,2L)
                );
    }

    @Test
    @DisplayName("등록된 팀에 직원이 없을 경우 팀명만 조회할 수 있고, 매니저 이름은 null,직원수는 0이다.")
    void searchTeamProfileWithOutMemberAndManager() {
        //given
        teamJpaRepository.save(new Team("팀A"));

        //when
        List<TeamProfileResponse> teamProfile = teamJpaRepository.getTeamProfile();

        //then
        assertThat(teamProfile).hasSize(1);
        assertThat(teamProfile).extracting("name","manager","memberCount")
                .containsExactlyInAnyOrder(
                        tuple("팀A",null,0L)
                );
    }

    private Employee createEmployee(String name, ROLE role) {
        return Employee.builder().name(name).role(role).build();
    }

}