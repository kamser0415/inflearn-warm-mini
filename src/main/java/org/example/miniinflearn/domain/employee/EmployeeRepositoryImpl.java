package org.example.miniinflearn.domain.employee;

import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
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
        return jpaRepository.findById(employeeId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 직원"));
    }

    @Override
    public List<EmployeeProfileResponse> getEmployeeProfileAll() {
        return jpaRepository.getEmployeeProfileAll();
    }
}
