package org.example.miniinflearn.api.timecard.service.response;

import lombok.Getter;

@Getter
public class AttendResponse {
    private final Long id;
    private final String name;

    public AttendResponse(Long timeCardId, String name) {
        this.id = timeCardId;
        this.name = name;
    }
}
