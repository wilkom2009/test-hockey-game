package com.maplr.testhockeygame.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.dto.TeamDto;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.model.Team;
import com.maplr.testhockeygame.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    /**
     * Persists or put a team object into DB
     * 
     * @param team
     * @return A saved TeamDto object
     */
    public TeamDto saveTeam(Team team) {
        Team saved = teamRepository.save(team);
        return saved == null ? null : new TeamDto(saved);
    }

    /**
     * Find a team dto by its year
     * 
     * @param year
     * @return A found TeamDto object from its year
     */
    public TeamDto getTeamByYear(Long year) {
        Optional<Team> foundTeam = teamRepository.findByYear(year);
        if (foundTeam.isPresent()) {
            TeamDto dto = new TeamDto(foundTeam.get());
            Set<Player> players = foundTeam.get().getPlayers();
            dto.setPlayers(
                    players == null ? null : players.stream().map(p -> new PlayerDto(p)).collect(Collectors.toSet()));
            return dto;
        }
        return null;
    }
}
