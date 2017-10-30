package com.agen.ttw.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "t_bg", schema = "dbo")
public class TBg implements java.io.Serializable {

	private BigDecimal id;
	private TMemberCyb TMemberCyb;
	private TMemberJcData TMemberJcData;
	private XxAdmin xxAdminByFxsId;
	private XxMember xxMember;
	private XxOrder xxOrder;
	private XxProduct xxProduct;
	private Date bgDate;
	private XxAdmin xxAdminByPzrId;
	private XxAdmin xxAdminByFxsIdCj;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fxs_id_cj")
	public XxAdmin getXxAdminByFxsIdCj() {
		return this.xxAdminByFxsIdCj;
	}

	public void setXxAdminByFxsIdCj(XxAdmin xxAdminByFxsIdCj) {
		this.xxAdminByFxsIdCj = xxAdminByFxsIdCj;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pzr_id")
	public XxAdmin getXxAdminByPzrId() {
		return this.xxAdminByPzrId;
	}

	public void setXxAdminByPzrId(XxAdmin xxAdminByPzrId) {
		this.xxAdminByPzrId = xxAdminByPzrId;
	}
	
	public TBg() {
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
	@JoinColumn(name = "cyb_id")
	public TMemberCyb getTMemberCyb() {
		return this.TMemberCyb;
	}

	public void setTMemberCyb(TMemberCyb TMemberCyb) {
		this.TMemberCyb = TMemberCyb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberjcdata_id")
	public TMemberJcData getTMemberJcData() {
		return this.TMemberJcData;
	}

	public void setTMemberJcData(TMemberJcData TMemberJcData) {
		this.TMemberJcData = TMemberJcData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fxs_id")
	public XxAdmin getXxAdminByFxsId() {
		return this.xxAdminByFxsId;
	}

	public void setXxAdminByFxsId(XxAdmin xxAdminByFxsId) {
		this.xxAdminByFxsId = xxAdminByFxsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public XxMember getXxMember() {
		return this.xxMember;
	}

	public void setXxMember(XxMember xxMember) {
		this.xxMember = xxMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	public XxOrder getXxOrder() {
		return this.xxOrder;
	}

	public void setXxOrder(XxOrder xxOrder) {
		this.xxOrder = xxOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public XxProduct getXxProduct() {
		return this.xxProduct;
	}

	public void setXxProduct(XxProduct xxProduct) {
		this.xxProduct = xxProduct;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bg_date", length = 23)
	public Date getBgDate() {
		return this.bgDate;
	}

	public void setBgDate(Date bgDate) {
		this.bgDate = bgDate;
	}


}
