package org.example.miniinflearn.api.employee.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.miniinflearn.domain.employee.ROLE;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeProfileResponse {

    private String name;
    private String teamName;
    private ROLE role;
    private LocalDate birthday;
    private LocalDate workStartDate;

}
