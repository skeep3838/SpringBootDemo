package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 商品Table
 *
 */
@Entity
@Table(name = "item")
//@NamedStoredProcedureQuery(name = "price_Range_Item", 
//procedureName = "price_Range_Item", resultClasses = Item.class,
//parameters = @StoredProcedureParameter(mode = ParameterMode.IN, name = "price_in", type = Integer.class))
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_item_iid")
	@SequenceGenerator(name="seq", sequenceName="seq_item_iid", allocationSize=1)
	@Column(name = "seq", unique = true, nullable = false)
	private Integer iid;
	
	@Column(name = "iname", unique = true, nullable = false)
	private String iname;
	
	@Column(name = "type", unique = true, nullable = false)
	private String type;
	
	@Column(name = "price", unique = true, nullable = false)
	private Integer price;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updateDate")
	private Date updateDate;

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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
