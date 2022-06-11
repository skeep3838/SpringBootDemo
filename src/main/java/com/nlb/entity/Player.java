package com.nlb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nlb_player")
public class Player {
	@Id
	@Column(name = "playerId", unique = true, nullable = false)
	private String playerId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "birthdayDate", nullable = false)
	private Date birthdayDate;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "team", nullable = false)
	private String team;

	@Column(name = "createdDate", nullable = false)
	private Date createdDate;

	@Column(name = "updateDate", nullable = false)
	private Date updateDate;

	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

}
