package com.nlb.serviceTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.response.ResponseBodyEntity;
import com.nlb.entity.Player;
import com.nlb.init.InitTestData;
import com.nlb.service.PlayerService;

class PlayerServiceTest {
	@Autowired
	private PlayerService playerService;

	@Test
	@Transactional
	void addPalyerTest() {
		ResponseBodyEntity<Player> playerRes = playerService.addPalyer(InitTestData.initPlayer());
		if(playerRes.getReturnData().isEmpty()) {
			fail("新增球員失敗");
		}
	}
}
