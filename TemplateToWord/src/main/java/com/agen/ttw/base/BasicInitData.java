package com.agen.ttw.base;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;

import com.agen.ttw.entity.TBg;
import com.agen.ttw.model.BasicInfo;
import com.agen.ttw.service.BgService;

/**
 * 
 * <label>
 *		基本数据封装类.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName BasicInitData
 * @author tgj  
 * @date 2017年10月30日 下午1:19:30 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
public class BasicInitData extends AbstractData {
	
	@Resource(name = "ttwBgService")
	private BgService bgService;
	
	@Override
	@Transactional
	public void initData(BasicInfo basicInfo) throws Exception {
		TBg bg = bgService.findOne(basicInfo.getBgId());
		Hibernate.initialize(bg.getXxProduct());
		Hibernate.initialize(bg.getXxAdminByFxsId());
		Hibernate.initialize(bg.getXxAdminByFxsIdCj());
		Hibernate.initialize(bg.getXxAdminByPzrId());
		Hibernate.initialize(bg.getTMemberCyb());
		Hibernate.initialize(bg.getTMemberCyb().getXxAdmin());
		Hibernate.initialize(bg.getTMemberJcData());
		Hibernate.initialize(bg.getXxMember());
		Hibernate.initialize(bg.getXxMember().getXxAdmin());
		Hibernate.initialize(bg.getXxOrder());
		basicInfo.getData().put("bg", bg);
	}

}
