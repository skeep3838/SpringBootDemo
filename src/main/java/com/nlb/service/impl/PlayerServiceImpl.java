package com.nlb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.response.ResponseBodyEntity;
import com.nlb.entity.Player;
import com.nlb.repository.PlayerRepository;
import com.nlb.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepository;

	@Transactional
	@Override
	public ResponseBodyEntity<Player> savePalyer(Player player) {
		ResponseBodyEntity<Player> res = new ResponseBodyEntity<Player>();
		List<Player> playerList = List.of(playerRepository.saveAndFlush(player));
		res.successSet();
		res.setReturnMessage("修改球員資料"+res.getReturnMessage());
		res.setReturnData(playerList);
		return res;
	}

	@Transactional
	@Override
	public ResponseBodyEntity<Player> addPalyer(Player player) {
		ResponseBodyEntity<Player> res = new ResponseBodyEntity<Player>();
		List<Player> playerList = List.of(playerRepository.saveAndFlush(player));
		res.successSet();
		res.setReturnMessage("新增球員資料"+res.getReturnMessage());
		res.setReturnData(playerList);
		return res;
	}

	@Override
	public ResponseBodyEntity<Player> selectActivePlayer(String status) {
		ResponseBodyEntity<Player> res = new ResponseBodyEntity<>();
		List<Player> playerList = playerRepository.findAll(queryPlayerBy(status));
		res.successSet();
		res.setReturnMessage("查詢球員清單"+res.getReturnMessage());
		res.setReturnData(playerList);
		return res;
	}

	@Override
	public ResponseBodyEntity<Player> selectPlayer(String playerId) {
		ResponseBodyEntity<Player> res = new ResponseBodyEntity<>();
		List<Player> playerList = List.of(playerRepository.findByPlayerId(playerId));
		res.successSet();
		res.setReturnMessage("查詢球員清單"+res.getReturnMessage());
		res.setReturnData(playerList);
		return res;
	}

	public Specification<Player> queryPlayerBy(String status) {
		return (playerRoot, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(status)) {
				predicates.add(cb.equal(playerRoot.get("type"), status));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
