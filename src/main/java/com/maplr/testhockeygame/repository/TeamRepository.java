package com.maplr.testhockeygame.repository;

import java.util.Optional;

import com.maplr.testhockeygame.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByYear(Long year);
}
