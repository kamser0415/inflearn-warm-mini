package org.example.miniinflearn.api.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.miniinflearn.api.employee.controller.request.CreateEmployeeRequest;
import org.example.miniinflearn.api.employee.service.EmployeeService;
import org.example.miniinflearn.domain.employee.ROLE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("직원 등록시 직원 이름은 필수 값이다.")
    void joinEmployeeWithoutName() throws Exception {
        //given
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .role(ROLE.MANAGER)
                .joinDate(LocalDate.now())
                .birthday(LocalDate.now())
                .build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("must not be blank"));
    }

    @Test
    @DisplayName("직원 등록시 직급은 필수 값이다.")
    void joinEmployeeWithoutRoll() throws Exception {
        //given
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .name("직원")
                .role(null)
                .joinDate(LocalDate.now())
                .birthday(LocalDate.now())
                .build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("must not be null"));
    }

    @Test
    @DisplayName("직원 등록시 가입 일은 필수 값이다.")
    void joinEmployeeWithoutJoinDate() throws Exception {
        //given
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .name("정원")
                .role(ROLE.MANAGER)
                .joinDate(null)
                .birthday(LocalDate.now())
                .build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("must not be null"));
    }

    @Test
    @DisplayName("직원 등록시 생일은 필수 값이다.")
    void joinEmployeeWithoutBirthday() throws Exception {
        //given
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .name("직원")
                .role(ROLE.MANAGER)
                .joinDate(LocalDate.now())
                .birthday(null)
                .build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("must not be null"));
    }

}