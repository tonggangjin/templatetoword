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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_member_jc_data", schema = "dbo")
public class TMemberJcData implements java.io.Serializable {

	private BigDecimal id;
	private Date createDate;
	private String jcdata1;
	private String jcdata2;
	private Set<TBg> TBgs = new HashSet<TBg>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TMemberJcData")
	public Set<TBg> getTBgs() {
		return this.TBgs;
	}

	public void setTBgs(Set<TBg> TBgs) {
		this.TBgs = TBgs;
	}
	
	@Column(name = "jcdata2")
	public String getJcdata2() {
		return this.jcdata2;
	}

	public void setJcdata2(String jcdata2) {
		this.jcdata2 = jcdata2;
	}

	@Column(name = "jcdata1")
	public String getJcdata1() {
		return this.jcdata1;
	}

	public void setJcdata1(String jcdata1) {
		this.jcdata1 = jcdata1;
	}
	
	public TMemberJcData() {
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, length = 23)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
