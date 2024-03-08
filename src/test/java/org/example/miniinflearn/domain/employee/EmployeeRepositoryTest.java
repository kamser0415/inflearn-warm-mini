package org.example.miniinflearn.domain.employee;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.example.miniinflearn.domain.team.Team;
import org.example.miniinflearn.domain.team.TeamJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;
    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @Test
    @DisplayName("팀에 속한 팀원을 조회시 팀명을 확인할 수 있다.")
    void findAllWithTeamName() {
        //given
        String employeeName = "팀원 A";
        String teamName = "팀A";
        ROLE manager = ROLE.MANAGER;
        LocalDate workStartDate = LocalDate.of(2023,4,15);
        LocalDate birthday = LocalDate.of(1988 ,4,13);

        Team teamA = teamJpaRepository.save(new Team(teamName));
        Employee employee = getEmployee(workStartDate, birthday, teamA, employeeName, manager);
        employeeJpaRepository.save(employee);

        //when
        List<EmployeeProfileResponse> employeeProfileAll = employeeJpaRepository.getEmployeeProfileAll();

        //then
        Assertions.assertThat(employeeProfileAll).hasSize(1);
        Assertions.assertThat(employeeProfileAll).extracting("name", "teamName", "role", "birthday", "workStartDate")
                .containsExactlyInAnyOrder(
                        Assertions.tuple(employeeName, teamName, manager, birthday, workStartDate)
                );

    }

    @Test
    @DisplayName("팀에 속하지 않은 팀원을 조회시 팀명은 null이 들어온다")
    void findAllWithoutTeam() {
        //given
        String employeeName = "팀원 A";
        ROLE manager = ROLE.MANAGER;
        LocalDate workStartDate = LocalDate.of(2023,4,15);
        LocalDate birthday = LocalDate.of(1988 ,4,13);

        Employee employee = getEmployee(workStartDate, birthday, employeeName, manager);
        employeeJpaRepository.save(employee);

        //when
        List<EmployeeProfileResponse> employeeProfileAll = employeeJpaRepository.getEmployeeProfileAll();

        //then
        Assertions.assertThat(employeeProfileAll).hasSize(1);
        Assertions.assertThat(employeeProfileAll).extracting("name", "teamName", "role", "birthday", "workStartDate")
                .containsExactlyInAnyOrder(
                        Assertions.tuple(employeeName, null, manager, birthday, workStartDate)
                );

    }

    private static Employee getEmployee(LocalDate workStartDate, LocalDate birthday, Team teamA, String name, ROLE role) {
        return Employee.builder()
                .name(name)
                .role(role)
                .workStartDate(workStartDate)
                .birthday(birthday)
                .teamId(teamA.getId()).build();
    }
    private static Employee getEmployee(LocalDate workStartDate, LocalDate birthday, String name, ROLE role) {
        return Employee.builder()
                .name(name)
                .role(role)
                .workStartDate(workStartDate)
                .birthday(birthday)
                .build();
    }
}