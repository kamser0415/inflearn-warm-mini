package org.example.miniinflearn.api.timecard.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.timecard.service.response.TimeCardService;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class TimeCardServiceTest {

    @Autowired
    private TimeCardService teamService;
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @AfterEach
    void setUp() {
        employeeJpaRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("등록된 직원이 아닌 사람이 출근할 경우 예외가 발생합니다.")
    void recordGoWork(){
        Long id = 1L;
        CreateTimeCardWorkRequest request = new CreateTimeCardWorkRequest(id);
        assertThatThrownBy(() -> teamService.recordGoWork(request))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("등록된 직원이 근무일에 처음 출근하면 기록됩니다.")
    void record1() {
        // given
        long employeeId = saveEmployee();
        CreateTimeCardWorkRequest request = new CreateTimeCardWorkRequest(employeeId);

        // when
        TimeCardWorkResponse timeCardWorkResponse = teamService.recordGoWork(request);

        // then
        assertThat(timeCardWorkResponse.getId()).isEqualTo(employeeId);
    }

    @Test
    @DisplayName("등록한 직원이 근무일에 다시 출근하면 기록되지 않습니다.")
    void record2 () {
        // given
        long employeeId = saveEmployee();
        CreateTimeCardWorkRequest request = new CreateTimeCardWorkRequest(employeeId);

        // when
        TimeCardWorkResponse timeCardWorkResponse = teamService.recordGoWork(request);

        // then
        assertThat(timeCardWorkResponse.getId()).isEqualTo(employeeId);
    }

    @Test
    @DisplayName("퇴근을 누르면 퇴근날짜가 기록됩니다.")
    void recordWithLease() {
        // given
        long employeeId = saveEmployee();
        CreateTimeCardWorkRequest request = new CreateTimeCardWorkRequest(employeeId);

        // when
        TimeCardWorkResponse response = teamService.recordleave(request);

        // then
        assertThat(response.getId()).isEqualTo(employeeId);
    }


    private Long saveEmployee() {
        Employee save = employeeJpaRepository.save(Employee.builder().build());
        return save.getId();
    }


}