package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "functions")
public class Function {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_function_fid")
	@SequenceGenerator(name="seq", sequenceName="seq_function_fid", allocationSize=1)
	@Column(name="seq", unique = true, nullable = false)
	private Integer fid;
	
	@Column(name="function_name", unique = true, nullable = false)
	private String name;
	
	@Column(name="description", unique = true, nullable = false)
	private String description;
	
	@Column(name="path", unique = true, nullable = false)
	private String path;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updateDate")
	private Date updateDate;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
