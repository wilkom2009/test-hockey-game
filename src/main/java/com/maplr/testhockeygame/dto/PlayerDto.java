package com.maplr.testhockeygame.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.model.PositionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to manage Players
 * 
 * @author Koffi
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long number;
    private String name;
    private String lastname;
    private PositionEnum position;
    private Boolean isCaptain;
    @JsonIgnore
    private TeamDto team;

    /**
     * Generate a player Dto object from a player object
     * 
     * @param player
     */
    public PlayerDto(Player player) {
        this.number = player.getNumber();
        this.name = player.getName();
        this.lastname = player.getLastname();
        this.position = player.getPosition();
        this.isCaptain = player.getIsCaptain();
        this.team = new TeamDto(player.getTeam());
    }

}
