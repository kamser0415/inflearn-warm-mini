package org.example.miniinflearn.api.employee.controller.request;

import lombok.Builder;
import lombok.Getter;
import org.example.miniinflearn.domain.employee.ROLE;

import java.time.LocalDate;

@Getter
public class EmployeeCreateRequest {
    private String name;
    private ROLE role;
    private LocalDate joinDate;
    private LocalDate birthday;
    private String teamName;

    public EmployeeCreateRequest() {
    }

    @Builder
    private EmployeeCreateRequest(final String name, final ROLE role, final LocalDate joinDate, final LocalDate birthday, String teamName) {
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthday = birthday;
        this.teamName = teamName;
    }

    public boolean hasTeam() {
        if (teamName == null || teamName.isBlank()) {
            return false;
        }
        return true;
    }

}
