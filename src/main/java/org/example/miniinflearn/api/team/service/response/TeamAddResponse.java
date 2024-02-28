package org.example.miniinflearn.api.team.service.response;

import lombok.Getter;

@Getter
public class TeamAddResponse {

    private long id;
    private String name;

    public TeamAddResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
