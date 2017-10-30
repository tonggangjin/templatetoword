package com.agen.ttw.base;

import java.io.File;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agen.ttw.entity.TBg;
import com.agen.ttw.model.BasicInfo;
import com.agen.ttw.service.BgService;

/**
 * 
 * <label>
 *		baseInfo数据初始化类.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBasicInfo
 * @author tgj  
 * @date 2017年10月30日 下午1:20:17 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBasicInfo")
public class InitBasicInfo {
	
	@Value(value = "${ttw.filePath}")
	private String filePath;
	
	@Resource(name = "ttwBgService")
	private BgService bgService;
	
	/**
	 * 为了兼容ftl，只能为doc
	 */
	private static final String FILETYPE = ".doc";
	
	public void fillBasicInfo(BasicInfo basicInfo) {
		BigDecimal bgId = basicInfo.getBgId();
		TBg bg = bgService.findOne(bgId);
		basicInfo.setOrderId(bg.getXxOrder().getId());
		basicInfo.setProductId(bg.getXxProduct().getId());
		basicInfo.setMemberId(bg.getXxMember().getId());
		basicInfo.setSampleId(bg.getTMemberCyb().getId());
		basicInfo.setInstitutionId(bg.getXxMember().getXxAdmin().getId());
		basicInfo.setPartFile(bgId + File.separator + bg.getXxOrder().getSn() + FILETYPE);
		basicInfo.setFile(new File(filePath, basicInfo.getPartFile()));
	}
	
}
