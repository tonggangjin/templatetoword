package com.agen.ttw.core;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.base.InitBasicInfo;
import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		处理之前.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseBefore
 * @author tgj  
 * @date 2017年10月30日 下午1:22:32 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseBefore")
public class InitBaseBefore extends AbstractProcedure {

	@Autowired
	@Qualifier("ttwInitBasicInfo")
	private InitBasicInfo initBasicInfo;
	
	@Override
	@Transactional
	public boolean process(BasicInfo basicInfo) throws Exception {
		initBasicInfo.fillBasicInfo(basicInfo);
		return procedure.process(basicInfo);
	}

}
