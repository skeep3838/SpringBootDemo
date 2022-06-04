package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 訂單細節
 *
 */
@Entity
@Table(name = "orderDetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_orderDetail_seq")
	@SequenceGenerator(name="seq", sequenceName="seq_orderDetail_seq", allocationSize=1)
	@Column(name = "seq", unique = true, nullable = false)
	private Integer odid;
	
	@Column(name = "qty", nullable = false)
	private Integer qty;
	
	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "iid", nullable = false)
	private Item item;

//	@ManyToOne(targetEntity = Orders.class)
//	@JoinColumn(name = "oid")
//	private Orders order;
	@Column(name = "oid", nullable = false)
	private String oid;

	public Integer getOdid() {
		return odid;
	}

	public void setOdid(Integer odid) {
		this.odid = odid;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

//	public Orders getOrder() {
//		return order;
//	}
//
//	public void setOrder(Orders order) {
//		this.order = order;
//	}

}
