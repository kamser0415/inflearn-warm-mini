package org.example.miniinflearn.domain.employee;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    private LocalDate workStartDate;
    private LocalDate birthday;
    private Long teamId;

    @Builder
    private Employee(final String name, final ROLE role, final LocalDate workStartDate, final LocalDate birthday, Long teamId) {
        this.name = name;
        this.role = role;
        this.workStartDate = workStartDate;
        this.birthday = birthday;
        this.teamId = teamId;
    }

    public static Employee create(final String name, final ROLE role, final LocalDate workStartDate, final LocalDate birthday) {
        return Employee.builder()
                .name(name)
                .role(role)
                .workStartDate(workStartDate)
                .birthday(birthday)
                .teamId(null)
                .build();
    }

    public void joinTeam(Long teamId) {
        this.teamId = teamId;
    }
}
