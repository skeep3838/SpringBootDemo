package com.nlb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "nlb_playerFieldingPosition")
public class PlayerFieldingPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_playerFieldingPosition")
	@SequenceGenerator(name = "seq", sequenceName = "seq_playerFieldingPosition", allocationSize = 1)
	@Column(name = "seq", unique = true, nullable = false)
	private Integer seq;

	@Column(name = "playerId", nullable = false)
	private String playerId;

	@Column(name = "fieldingPosition", nullable = false)
	private String fieldingPosition;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getFieldingPosition() {
		return fieldingPosition;
	}

	public void setFieldingPosition(String fieldingPosition) {
		this.fieldingPosition = fieldingPosition;
	}

}
