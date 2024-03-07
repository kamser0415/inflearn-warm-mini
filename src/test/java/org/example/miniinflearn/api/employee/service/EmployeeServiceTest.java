package org.example.miniinflearn.api.employee.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.employee.controller.request.EmployeeCreateRequest;
import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.example.miniinflearn.api.team.service.TeamService;
import org.example.miniinflearn.domain.employee.ROLE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @Test
    @DisplayName("직원을 등록할 수 있다.")
    void joinEmployee() {
        // given
        String name = "둘리";
        ROLE role = ROLE.MANAGER;
        LocalDate joinDate = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 3, 4);
        EmployeeCreateRequest request = getEmployeeCreateRequest(name, role, joinDate, birthday);

        // when
        long joinEmployeeId = employeeService.joinEmployee(request);

        // then
        Assertions.assertThat(joinEmployeeId).isEqualTo(1L);
    }

    private EmployeeCreateRequest getEmployeeCreateRequest(String name, ROLE role, LocalDate joinDate, LocalDate birthday) {
        return EmployeeCreateRequest.builder()
                .name(name)
                .role(role)
                .joinDate(joinDate)
                .birthday(birthday)
                .build();
    }

    @Test
    @DisplayName("팀원 정보를 모두 조회할 수 있다.")
    void getMemberAll() {
        //given
        teamService.addTeam("팀A");
        teamService.addTeam("팀B");

        employeeService.joinEmployee(getEmployeeCreateRequest("팀A", "매니저-둘리", ROLE.MANAGER, LocalDate.of(1998, 8, 1), LocalDate.of(2024, 1, 1)));
        employeeService.joinEmployee(getEmployeeCreateRequest("팀A", "직원-둘리", ROLE.MEMBER, LocalDate.of(1997, 8, 1), LocalDate.of(2024, 3, 1)));

        //when
        List<EmployeeProfileResponse> responses = employeeService.getEmployeeProfileAll();

        for (EmployeeProfileResponse respons : responses) {
            System.out.println("respons = " + respons);
        }

        //then
        Assertions.assertThat(responses).hasSize(2);
        Assertions.assertThat(responses)
                .extracting("name", "teamName", "role", "birthday", "workStartDate")
                .containsExactlyInAnyOrder(
                        Assertions.tuple("매니저-둘리", "팀A", "MANAGER", "1998-08-01", "2024-01-01"),
                        Assertions.tuple("직원-둘리", "팀A", "MEMBER", "1997-08-01", "2024-03-01")
                );
    }

    private EmployeeCreateRequest getEmployeeCreateRequest(String teamName, String name, ROLE role, LocalDate birthday, LocalDate workStartDate) {
        return EmployeeCreateRequest.builder()
                .teamName(teamName)
                .name(name)
                .role(role)
                .birthday(birthday)
                .joinDate(workStartDate)
                .build();
    }
}
