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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xx_order", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = "sn"))
public class XxOrder implements java.io.Serializable {

	private BigDecimal id;
	private Set<TBg> TBgs = new HashSet<TBg>(0);
	private Set<TMemberJcData> TMemberJcDatas = new HashSet<TMemberJcData>(0);
	private String sn;
	@Column(name = "sn", unique = true, nullable = false, length = 100)
	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public XxOrder() {
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxOrder")
	public Set<TBg> getTBgs() {
		return this.TBgs;
	}

	public void setTBgs(Set<TBg> TBgs) {
		this.TBgs = TBgs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxOrder")
	public Set<TMemberJcData> getTMemberJcDatas() {
		return this.TMemberJcDatas;
	}

	public void setTMemberJcDatas(Set<TMemberJcData> TMemberJcDatas) {
		this.TMemberJcDatas = TMemberJcDatas;
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
	

}
