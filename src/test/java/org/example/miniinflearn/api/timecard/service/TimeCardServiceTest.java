package org.example.miniinflearn.api.timecard.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.timecard.service.response.TimeCardService;
import org.example.miniinflearn.domain.employee.EmployeeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeCardServiceTest {

    @Autowired
    private TimeCardService teamService;
    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Test
    @DisplayName("출근하다.")
    void recordGoWork(){
        Long id = 1L;
        CreateTimeCardWorkRequest request = new CreateTimeCardWorkRequest(id);
        TimeCardWorkResponse timeCardWorkResponse = teamService.recordGoWork(request);
        Assertions.assertThat(timeCardWorkResponse.getId()).isEqualTo(1L);
    }

}