package com.nlb.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nlb.entity.Player;
import com.nlb.entity.Teams;
import com.nlb.nlbEnum.PlayerStatus;

public class InitTestData {
	public static Player initPlayer() {
		Player player = new Player();
		player.setPlayerId("A111111111");
		player.setBirthdayDate(parseDate("1994-07-05"));
		player.setName("大谷翔平");
		player.setStatus(PlayerStatus.ACTIVE.getValue());
		player.setTeam(Teams.LOS_ANGELES_ANGELS.getValue());
		player.setCreatedDate(parseDate("2018-03-29"));
		player.setUpdateDate(new Date());
		return player;
	}

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
