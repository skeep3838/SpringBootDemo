package com.nlb.service;

import com.example.response.ResponseBodyEntity;
import com.nlb.entity.Player;

public interface PlayerService {
	ResponseBodyEntity<Player> addPalyer(Player player);
	ResponseBodyEntity<Player> savePalyer(Player player);
	ResponseBodyEntity<Player> selectPlayer(String playerId);
	ResponseBodyEntity<Player> selectActivePlayer(String status);
}
