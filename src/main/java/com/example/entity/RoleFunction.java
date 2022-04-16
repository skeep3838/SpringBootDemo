package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role_function")
public class RoleFunction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_role_function")
	@SequenceGenerator(name="seq", sequenceName="seq_role_function", allocationSize=1)
	@Column(name="seq", unique = true, nullable = false)
	private Integer seq;
	
	@Column(name="role_seq", nullable = false)
	private Integer rid;
	
	@Column(name="function_seq", nullable = false)
	private Integer fid;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	
}
