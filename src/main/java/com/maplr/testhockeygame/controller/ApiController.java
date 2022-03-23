package com.maplr.testhockeygame.controller;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.dto.TeamDto;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.model.Team;
import com.maplr.testhockeygame.service.PlayerService;
import com.maplr.testhockeygame.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global API controller class to expose endpoints
 * 
 * @author Koffi
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    /**
     * Exposes /api/team/{year} endpoint
     * 
     * @param year
     * @return a Team json object
     */
    @GetMapping("/team/{year}")
    public ResponseEntity<TeamDto> getTeamFromAYear(@PathVariable Long year) {
        TeamDto dto = teamService.getTeamByYear(year);
        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Exposes /api/team/{year} endpoint
     * 
     * @param year
     * @param playerDto
     * @return a Created player json object if year found
     */
    @PostMapping("/team/{Year}")
    public ResponseEntity<PlayerDto> savePlayerDto(@PathVariable("Year") Long year, @RequestBody PlayerDto playerDto) {
        TeamDto teamDto = teamService.getTeamByYear(year);
        if (teamDto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Team team = new Team(teamDto.getId(), teamDto.getCoach(), teamDto.getYear(), null);
        Player player = new Player(playerDto.getNumber(), playerDto.getName(), playerDto.getLastname(),
                playerDto.getPosition(), playerDto.getIsCaptain(), team);

        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.CREATED);
    }

    /**
     * Exposes /api/player/captain/{ID} endpoint
     * 
     * @param id
     * @return a PlayerDto json object if found
     */
    @PutMapping("/player/captain/{ID}")
    public ResponseEntity<PlayerDto> updateYearTeamCaptain(@PathVariable("ID") Long id) {
        PlayerDto playerDto = playerService.getPlayerById(id);
        if (playerDto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Player player = new Player(playerDto.getNumber(), playerDto.getName(), playerDto.getLastname(),
                playerDto.getPosition(), playerDto.getIsCaptain(), null);
        Team team = new Team(playerDto.getTeam().getId(), playerDto.getTeam().getCoach(), playerDto.getTeam().getYear(),
                null);
        player.setIsCaptain(true);
        player.setTeam(team);

        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.OK);
    }
}
