package com.agen.ttw.core;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.base.AbstractProduce;
import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		模版处理.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseProduce
 * @author tgj  
 * @date 2017年10月30日 下午1:23:15 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseProduce")
public class InitBaseProduce extends AbstractProcedure {

	private static Map<String, AbstractProduce> produceMap;

	@Autowired
	public void init(ApplicationContext applicationContext) {
		InitBaseProduce.produceMap = applicationContext.getBeansOfType(AbstractProduce.class);
	}
	
	@Override
	public boolean process(BasicInfo basicInfo) throws Exception {
		
		String tempName = StringUtils.substringBefore(basicInfo.getTemplateName(), ".");
		for (Entry<String, AbstractProduce> en : produceMap.entrySet()) {
			if (en.getValue().validate(tempName)) {
				en.getValue().produce(basicInfo);
				return procedure.process(basicInfo);
			}
		}
		return false;
	}

}
