package com.nlb.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.ResponseBodyEntity;
import com.nlb.entity.Player;
import com.nlb.nlbEnum.PlayerStatus;
import com.nlb.service.PlayerService;

@RestController
@RequestMapping("customer")
public class PlayerController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private PlayerService playerService;

	/*		查詢活動中的球員	*/
	@GetMapping("/selectActivePlayer")
	public ResponseBodyEntity<Player> selectActivePlayer(HttpServletRequest request) {
		return playerService.selectActivePlayer(PlayerStatus.ACTIVE.getValue());
	}

	/*		查詢單一球員	*/
	@PostMapping("/selectPlayer")
	public ResponseBodyEntity<Player> selectPlayer(HttpServletRequest request, @RequestParam("plarerId") String plarerId) {
		return playerService.selectPlayer(plarerId);
	}

	/*		新增球員球員	*/
	@PostMapping("/savePlayer")
	public ResponseBodyEntity<Player> savePalyer(HttpServletRequest request, @RequestBody Player plarer) {
		return playerService.savePalyer(plarer);
	}
}
