package org.example.miniinflearn.api.employee.controller;

import org.example.miniinflearn.api.ApiResponse;
import org.example.miniinflearn.api.employee.controller.request.EmployeeCreateRequest;
import org.example.miniinflearn.api.employee.service.response.EmployeeProfileResponse;
import org.example.miniinflearn.api.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/list")
    public ApiResponse<List<EmployeeProfileResponse>> getEmployeeProfileAll() {
        return ApiResponse.ok(employeeService.getEmployeeProfileAll());
    }

    @PostMapping("/employee")
    public ApiResponse<Long> joinEmployee(@RequestBody EmployeeCreateRequest request) {
        return ApiResponse.ok(employeeService.joinEmployee(request));
    }
}
