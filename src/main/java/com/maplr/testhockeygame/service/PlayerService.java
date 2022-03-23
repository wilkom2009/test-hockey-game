package com.maplr.testhockeygame.service;

import java.util.Optional;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Persists a player into the Db
     * 
     * @param player
     * @return A PlayerDto object
     */
    public PlayerDto savePlayer(Player player) {
        Player saved = playerRepository.save(player);
        return saved == null ? null : new PlayerDto(saved);
    }

    /**
     * Find a player in DB from his ID
     * 
     * @param id
     * @return A PlayerDto object
     */
    public PlayerDto getPlayerById(Long id) {
        Optional<Player> found = playerRepository.findById(id);
        return found.isEmpty() ? null : new PlayerDto(found.get());
    }
}
