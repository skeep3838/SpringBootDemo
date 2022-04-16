package com.example.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_role_rid")
	@SequenceGenerator(name="seq", sequenceName="seq_role_rid", allocationSize=1)
	@Column(name="seq", unique = true, nullable = false)
	private Integer rid;
	
	@Column(name="role_name", unique = true, nullable = false)
	private String name;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updateDate")
	private Date updateDate;
	
	@ManyToMany
    @JoinTable(
        name = "role_function", 
        joinColumns = @JoinColumn(
          name = "rid", referencedColumnName = "seq"), 
        inverseJoinColumns = @JoinColumn(
          name = "fid", referencedColumnName = "seq"))
    private Collection<Function> functions;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
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

	public Collection<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Collection<Function> functions) {
		this.functions = functions;
	}
	
	
}
