package org.example.miniinflearn.domain.employee;

import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;

import java.util.List;

public interface EmployeeRepository {
    long save(Employee employee);

    Employee findById(long employeeId);

    List<EmployeeProfileResponse> getEmployeeProfileAll();
}
