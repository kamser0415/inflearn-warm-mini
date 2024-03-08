package org.example.miniinflearn.api.employee.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.example.miniinflearn.domain.employee.ROLE;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
public class CreateEmployeeRequest {

    @NotBlank
    private String name;
    @NotNull
    private ROLE role;
    @NotNull
    private LocalDate joinDate;
    @NotNull
    private LocalDate birthday;
    private String teamName;

    public CreateEmployeeRequest() {
    }

    @Builder
    private CreateEmployeeRequest(final String name, final ROLE role, final LocalDate joinDate, final LocalDate birthday, String teamName) {
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
