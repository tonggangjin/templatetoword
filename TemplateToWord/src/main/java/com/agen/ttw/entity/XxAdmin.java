package com.agen.ttw.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xx_admin", schema = "dbo")
public class XxAdmin implements java.io.Serializable {

	private BigDecimal id;
	private Set<XxMember> xxMembers = new HashSet<XxMember>(0);
	private String name;
	
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxAdmin")
	public Set<XxMember> getXxMembers() {
		return this.xxMembers;
	}

	public void setXxMembers(Set<XxMember> xxMembers) {
		this.xxMembers = xxMembers;
	}
	
	
}
