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
@Table(name = "xx_product", schema = "dbo")
public class XxProduct implements java.io.Serializable {

	private BigDecimal id;
	private String fullName;
	private String name;
	private Set<TBg> TBgs = new HashSet<TBg>(0);

	public XxProduct() {
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


	@Column(name = "full_name", nullable = false)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxProduct")
	public Set<TBg> getTBgs() {
		return this.TBgs;
	}

	public void setTBgs(Set<TBg> TBgs) {
		this.TBgs = TBgs;
	}
	
	private Set<TDiseaseGene> TDiseaseGenes = new HashSet<TDiseaseGene>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xxProduct")
	public Set<TDiseaseGene> getTDiseaseGenes() {
		return this.TDiseaseGenes;
	}

	public void setTDiseaseGenes(Set<TDiseaseGene> TDiseaseGenes) {
		this.TDiseaseGenes = TDiseaseGenes;
	}


}
