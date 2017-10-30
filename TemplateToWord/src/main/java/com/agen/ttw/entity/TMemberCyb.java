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
@Table(name = "t_member_cyb", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = "cybbm"))
public class TMemberCyb implements java.io.Serializable {

	private BigDecimal id;
	private String cybbm;
	private Set<TBg> TBgs = new HashSet<TBg>(0);
	private XxAdmin xxAdmin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hzjg_id")
	public XxAdmin getXxAdmin() {
		return this.xxAdmin;
	}

	public void setXxAdmin(XxAdmin xxAdmin) {
		this.xxAdmin = xxAdmin;
	}

	public TMemberCyb() {
	}
	
	@Column(name = "cybbm", unique = true, length = 100)
	public String getCybbm() {
		return this.cybbm;
	}

	public void setCybbm(String cybbm) {
		this.cybbm = cybbm;
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

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TMemberCyb")
	public Set<TBg> getTBgs() {
		return this.TBgs;
	}

	public void setTBgs(Set<TBg> TBgs) {
		this.TBgs = TBgs;
	}
	
	private Date sjDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sj_date", length = 23)
	public Date getSjDate() {
		return this.sjDate;
	}

	public void setSjDate(Date sjDate) {
		this.sjDate = sjDate;
	}


}
