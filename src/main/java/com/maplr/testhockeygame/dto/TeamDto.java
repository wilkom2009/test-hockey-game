package com.maplr.testhockeygame.dto;

import java.util.Set;

import com.maplr.testhockeygame.model.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO class to manage Players
 * 
 * @author Koffi
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private long id;
    private String coach;
    private long year;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PlayerDto> players;

    /**
     * Generate a team Dto object from a team object
     * 
     * @param team
     */
    public TeamDto(Team team) {
        this.id = team.getId();
        this.coach = team.getCoach();
        this.year = team.getYear();
    }
}
