package com.agen.ttw.core;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.base.AbstractData;
import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		数组数据.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseData
 * @author tgj  
 * @date 2017年10月30日 下午1:22:44 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseData")
public class InitBaseData extends AbstractProcedure {
	
	private static Map<String, AbstractData> dataMap;

	@Autowired
	public void init(ApplicationContext applicationContext) {
		InitBaseData.dataMap = applicationContext.getBeansOfType(AbstractData.class);
	}

	@Override
	public boolean process(BasicInfo basicInfo) throws Exception {
		
		String tempName = StringUtils.substringBefore(basicInfo.getTemplateName(), ".");
		for (Entry<String, AbstractData> en : dataMap.entrySet()) {
			if (en.getValue().validate(tempName)) {
				en.getValue().initData(basicInfo);
				return procedure.process(basicInfo);
			}
		}
		
		return false;
	}

}
