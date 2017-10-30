package com.agen.ttw.core;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.base.AbstractTemplate;
import com.agen.ttw.model.BasicInfo;
import com.agen.ttw.template.GenericTemplate;

/**
 * 
 * <label>
 *		选择模版.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseTemplate
 * @author tgj  
 * @date 2017年10月30日 下午1:23:31 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseTemplate")
public class InitBaseTemplate extends AbstractProcedure {

	private static Map<String, AbstractTemplate> templateMap;

	@Autowired
	public void init(ApplicationContext applicationContext) {
		InitBaseTemplate.templateMap = applicationContext.getBeansOfType(AbstractTemplate.class);
	}

	@Override
	public boolean process(BasicInfo basicInfo) throws Exception {
		String templatePath = null, genericTemplateName = null;
		try {
			for (Entry<String, AbstractTemplate> en : templateMap.entrySet()) {
				if (!(en.getValue() instanceof GenericTemplate)) {
					templatePath = en.getValue().initTemplate(basicInfo);
					if (StringUtils.isNotBlank(templatePath)) {
						basicInfo.setTemplateName(templatePath);
						return procedure.process(basicInfo);
					}
				} else {
					genericTemplateName = en.getKey();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		templatePath = templateMap.get(genericTemplateName).initTemplate(basicInfo);
		basicInfo.setTemplateName(templatePath);
		
		return procedure.process(basicInfo);
	}

}
