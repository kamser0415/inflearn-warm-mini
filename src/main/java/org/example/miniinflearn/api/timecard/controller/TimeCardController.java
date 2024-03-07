package org.example.miniinflearn.api.timecard.controller;

import lombok.RequiredArgsConstructor;
import org.example.miniinflearn.api.ApiResponse;
import org.example.miniinflearn.api.timecard.controller.request.CreateAttendRequest;
import org.example.miniinflearn.api.timecard.service.response.AttendResponse;
import org.example.miniinflearn.api.timecard.service.TimeCardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TimeCardController {

    private final TimeCardService timeCardService;

    @PostMapping("/time-card")
    public ApiResponse<AttendResponse> recordAttend(CreateAttendRequest request) {
        return ApiResponse.ok(timeCardService.recordAttendance(request));
    }

}
