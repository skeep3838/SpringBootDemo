package com.nlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nlb.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {
	Player findByPlayerId(String playerId);
}
