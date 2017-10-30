package com.agen.ttw.core;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.base.AbstractFilter;
import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		过滤处理.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseFilter
 * @author tgj  
 * @date 2017年10月30日 下午1:22:57 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseFilter")
public class InitBaseFilter extends AbstractProcedure {
	
	private static Map<String, AbstractFilter> filterMap;

	@Autowired
	public void init(ApplicationContext applicationContext) {
		InitBaseFilter.filterMap = applicationContext.getBeansOfType(AbstractFilter.class);
	}
	
	@Override
	public boolean process(BasicInfo basicInfo) throws Exception {
		
		String tempName = StringUtils.substringBefore(basicInfo.getTemplateName(), ".");
		for (Entry<String, AbstractFilter> en : filterMap.entrySet()) {
			if (en.getValue().validate(tempName)) en.getValue().filter(basicInfo);
		}
		
		return procedure.process(basicInfo);
		
	}

}
