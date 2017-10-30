package com.agen.ttw.model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class BasicInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal bgId;
	private BigDecimal ProductId;
	private BigDecimal institutionId;
	private BigDecimal sampleId;
	private BigDecimal memberId;
	private BigDecimal orderId;
	private String partFile;
	private String templateName;
	private Map<String, Object> data = new HashMap<>();
	public Map<String, Object> getData() {
		return data;
	}
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPartFile() {
		return partFile;
	}
	public void setPartFile(String partFile) {
		this.partFile = partFile;
	}
	public BigDecimal getBgId() {
		return bgId;
	}
	public void setBgId(BigDecimal bgId) {
		this.bgId = bgId;
	}
	public BigDecimal getProductId() {
		return ProductId;
	}
	public void setProductId(BigDecimal productId) {
		ProductId = productId;
	}
	public BigDecimal getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(BigDecimal institutionId) {
		this.institutionId = institutionId;
	}
	public BigDecimal getSampleId() {
		return sampleId;
	}
	public void setSampleId(BigDecimal sampleId) {
		this.sampleId = sampleId;
	}
	public BigDecimal getMemberId() {
		return memberId;
	}
	public void setMemberId(BigDecimal memberId) {
		this.memberId = memberId;
	}
	public BigDecimal getOrderId() {
		return orderId;
	}
	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}
	public BasicInfo() {
		super();
	}

	private File file;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "BasicInfo [bgId=" + bgId + ", ProductId=" + ProductId + ", institutionId=" + institutionId
				+ ", sampleId=" + sampleId + ", memberId=" + memberId + ", orderId=" + orderId + ", partFile="
				+ partFile + ", templatePath=" + templateName + ", file=" + file + "]";
	}
	
	
}