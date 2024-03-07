package org.example.miniinflearn.api.team.service.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TeamProfileResponse {
    private String name;
    private String manager;
    private Long memberCount;

    public TeamProfileResponse(final String name, final String manager, final Long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

}
