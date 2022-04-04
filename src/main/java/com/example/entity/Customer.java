package com.example.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_customer_cid")
	@SequenceGenerator(name="seq", sequenceName="seq_customer_cid", allocationSize=1)
	@Column(name="seq", unique = true, nullable = false)
	private Integer cid;
	
	@Column(name="user_name", unique = true, nullable = false)
	private String userName;
	
	@Column(name="password", unique = true, nullable = false)
	private String password;

	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updateDate")
	private Date updateDate;
	
//	 將資料以typeID欄位 ASC方式排列後再寫進Set內
	@OneToMany(mappedBy="customer", orphanRemoval = false)
	@OrderBy("oid")
	private Set<Orders> orderList;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

//	public Set<Orders> getOrderList() {
//		return orderList;
//	}
//
//	public void setOrderList(Set<Orders> orderList) {
//		this.orderList = orderList;
//	}

}
