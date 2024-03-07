package org.example.miniinflearn.domain.employee;

import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {


    @Query("select " +
            "new org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse(" +
            "e.name,t.name,str(e.role),cast(e.birthday as String),cast(e.workStartDate as String))" +
            "from Employee as e left join Team as t on e.teamId = t.id")
    List<EmployeeProfileResponse> getEmployeeProfileAll();
}
