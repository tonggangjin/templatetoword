package com.agen.ttw.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xx_member", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = "memberno"))
public class XxMember implements java.io.Serializable {

	private BigDecimal id;
	private XxAdmin xxAdmin;
	private Set<TBg> TBgs = new HashSet<TBg>(0);

	public XxMember() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institutions")
	public XxAdmin getXxAdmin() {
		return this.xxAdmin;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxMember")
	public Set<TBg> getTBgs() {
		return this.TBgs;
	}

	public void setTBgs(Set<TBg> TBgs) {
		this.TBgs = TBgs;
	}

	public void setXxAdmin(XxAdmin xxAdmin) {
		this.xxAdmin = xxAdmin;
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

	private String name;

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String memberno;

	@Column(name = "memberno", length = 50)
	public String getMemberno() {
		return this.memberno;
	}

	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}

	private Date birth;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth", length = 23)
	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	private Integer gender;

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

}
