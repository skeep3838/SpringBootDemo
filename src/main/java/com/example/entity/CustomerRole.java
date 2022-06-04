package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer_role")
public class CustomerRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_customer_role")
	@SequenceGenerator(name="seq", sequenceName="seq_customer_role", allocationSize=1)
	@Column(name="seq", unique = true, nullable = false)
	private Integer seq;
	
	@Column(name="customer_seq", nullable = false)
	private Integer cid;
	
	@Column(name="role_seq", nullable = false)
	private Integer rid;

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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

}
