package org.example.miniinflearn.api.employee.service;

import org.example.miniinflearn.api.employee.controller.request.CreateEmployeeRequest;
import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeRepository;
import org.example.miniinflearn.domain.team.Team;
import org.example.miniinflearn.domain.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    public long joinEmployee(CreateEmployeeRequest request) {
        Employee employee = Employee.create(request.getName(), request.getRole(), request.getJoinDate(), request.getBirthday());
        ifHasTeamThenJoinTeam(request, employee);
        return employeeRepository.save(employee);
    }

    private void ifHasTeamThenJoinTeam(CreateEmployeeRequest request, Employee employee) {
        if (request.hasTeam()) {
            Team team = teamRepository.findByName(request.getTeamName())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀명입니다."));
            employee.joinTeam(team.getId());
        }
    }

    public List<EmployeeProfileResponse> getEmployeeProfileAll() {
        return employeeRepository.getEmployeeProfileAll();
    }
}
