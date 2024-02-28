package org.example.miniinflearn.domain.team;

import lombok.Getter;

@Getter
public class Team {
    private Long id;
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void assignId(long id) {
        this.id = id;
    }
}
