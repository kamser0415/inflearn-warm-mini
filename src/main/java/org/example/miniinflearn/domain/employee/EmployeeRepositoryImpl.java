package org.example.miniinflearn.domain.employee;

import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeJpaRepository jpaRepository;

    public EmployeeRepositoryImpl(EmployeeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public long save(Employee employee) {
        jpaRepository.save(employee);
        return employee.getId();
    }

    @Override
    public Employee findById(long employeeId) {
        return jpaRepository.findById(employeeId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<EmployeeProfileResponse> getEmployeeProfileAll() {
        return jpaRepository.getEmployeeProfileAll();
    }
}
