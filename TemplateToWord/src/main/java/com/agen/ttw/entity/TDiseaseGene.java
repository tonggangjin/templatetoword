package com.agen.ttw.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_disease_gene", schema = "dbo")
public class TDiseaseGene implements java.io.Serializable {

	private BigDecimal id;
	private XxProduct xxProduct;
	private String genevalue1;
	private Integer genevalue1type;
	private String genevalue2;
	private Integer genevalue2type;
	private String genevalue3;
	private Integer genevalue3type;
	private BigDecimal genetimes1;
	private BigDecimal genetimes2;
	private BigDecimal genetimes3;
	private Integer bh;
	
	@Column(name = "bh")
	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public TDiseaseGene() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public XxProduct getXxProduct() {
		return this.xxProduct;
	}

	public void setXxProduct(XxProduct xxProduct) {
		this.xxProduct = xxProduct;
	}

	@Column(name = "genevalue1")
	public String getGenevalue1() {
		return this.genevalue1;
	}

	public void setGenevalue1(String genevalue1) {
		this.genevalue1 = genevalue1;
	}

	@Column(name = "genevalue1type")
	public Integer getGenevalue1type() {
		return this.genevalue1type;
	}

	public void setGenevalue1type(Integer genevalue1type) {
		this.genevalue1type = genevalue1type;
	}

	@Column(name = "genevalue2")
	public String getGenevalue2() {
		return this.genevalue2;
	}

	public void setGenevalue2(String genevalue2) {
		this.genevalue2 = genevalue2;
	}

	@Column(name = "genevalue2type")
	public Integer getGenevalue2type() {
		return this.genevalue2type;
	}

	public void setGenevalue2type(Integer genevalue2type) {
		this.genevalue2type = genevalue2type;
	}

	@Column(name = "genevalue3")
	public String getGenevalue3() {
		return this.genevalue3;
	}

	public void setGenevalue3(String genevalue3) {
		this.genevalue3 = genevalue3;
	}

	@Column(name = "genevalue3type")
	public Integer getGenevalue3type() {
		return this.genevalue3type;
	}

	public void setGenevalue3type(Integer genevalue3type) {
		this.genevalue3type = genevalue3type;
	}


	@Column(name = "genetimes1", precision = 10, scale = 5)
	public BigDecimal getGenetimes1() {
		return this.genetimes1;
	}

	public void setGenetimes1(BigDecimal genetimes1) {
		this.genetimes1 = genetimes1;
	}

	@Column(name = "genetimes2", precision = 10, scale = 5)
	public BigDecimal getGenetimes2() {
		return this.genetimes2;
	}

	public void setGenetimes2(BigDecimal genetimes2) {
		this.genetimes2 = genetimes2;
	}

	@Column(name = "genetimes3", precision = 10, scale = 5)
	public BigDecimal getGenetimes3() {
		return this.genetimes3;
	}

	public void setGenetimes3(BigDecimal genetimes3) {
		this.genetimes3 = genetimes3;
	}

}
