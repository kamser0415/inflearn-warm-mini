package org.example.miniinflearn.api.employee.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeProfileResponse {

    private String name;
    private String teamName;
    private String role;
    private String birthday;
    private String workStartDate;

}
