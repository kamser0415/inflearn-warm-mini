package org.example.miniinflearn.api.timecard.service;

import org.assertj.core.api.Assertions;
import org.example.miniinflearn.api.timecard.controller.request.CreateAttendRequest;
import org.example.miniinflearn.api.timecard.service.response.AttendResponse;
import org.example.miniinflearn.domain.employee.Employee;
import org.example.miniinflearn.domain.employee.EmployeeRepository;
import org.example.miniinflearn.domain.employee.ROLE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class TimeCardServiceTest {

    @Autowired
    private TimeCardService timecardService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("사원은 출근을 할 수 있다.")
    void recordAttend() {
        //given
        Long id = 1L;
        String attendName = "attendName";
        CreateAttendRequest request = new CreateAttendRequest(id);
        employeeRepository.save(Employee.create(attendName, ROLE.MANAGER, LocalDate.now(), LocalDate.now()));

        //when
        AttendResponse attendResponse = timecardService.recordAttendance(request);

        //then
        Assertions.assertThat(attendResponse.getId()).isEqualTo(id);
        Assertions.assertThat(attendResponse.getName()).isEqualTo(attendName);
    }

    @Test
    @DisplayName("사원은 퇴근을 할 수 있다.")
    void leaveEmployee() {
        //given
        Long id = 1L;
        String attendName = "attendName";
        CreateAttendRequest request = new CreateAttendRequest(id);
        employeeRepository.save(Employee.create(attendName, ROLE.MANAGER, LocalDate.now(), LocalDate.now()));
        timecardService.recordAttendance(request);

        //when
        AttendResponse attendResponse = timecardService.recordLeave(request);

        //then
        Assertions.assertThat(attendResponse.getId()).isEqualTo(id);
        Assertions.assertThat(attendResponse.getName()).isEqualTo(attendName);
    }
}