package org.example.miniinflearn.domain.team;

import org.example.miniinflearn.domain.team.query.TeamProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamJpaRepository extends JpaRepository<Team, Long>{
    Optional<Team> findByName(String name);

    @Query("select " +
            "new org.example.miniinflearn.domain.team.query.TeamProfileResponse(" +
            "t.name, " +
            "function('listagg', case when e.role = MANAGER then e.name else null end,','),"+
            "SUM(CASE WHEN e.teamId is not null then 1L ELSE 0L END)) " +
            "from Team as t " +
            "left join Employee as e on t.id = e.teamId GROUP BY t.name")
    List<TeamProfileResponse> getTeamProfile();
}
