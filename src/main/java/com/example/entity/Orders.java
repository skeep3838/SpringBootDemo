package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * 訂單資訊
 */
@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_orders_oid")
	@SequenceGenerator(name = "seq", sequenceName = "seq_orders_oid", allocationSize = 1)
	@Column(name = "seq", unique = true, nullable = false)
	private Integer oid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)
	private Customer customer;

//	fetch = FetchType.EAGER 加入這個會導致撈出的Bean式舊的資料
//	mappedBy="orderMap",  => 在Itemline 找到對應的 private Orders orderMap
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "oid")
	@OrderBy("seq")
	private List<OrderDetail> orders;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "updateDate")
	private Date updateDate;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetail> orders) {
		this.orders = orders;
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

}
