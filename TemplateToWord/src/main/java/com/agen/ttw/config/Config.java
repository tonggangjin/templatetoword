package com.agen.ttw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.core.InitBaseAfter;
import com.agen.ttw.core.InitBaseBefore;
import com.agen.ttw.core.InitBaseData;
import com.agen.ttw.core.InitBaseFilter;
import com.agen.ttw.core.InitBaseProduce;
import com.agen.ttw.core.InitBaseTemplate;

/**
 * 
 * <label> ttw配置文件. </label>
 * <p>
 * 
 * <pre>
 * 主要配置都由此管理
 * </pre>
 * </p>
 * 
 * @ClassName Config
 * @author TGJ
 * @date 2017年10月25日 上午11:19:24
 * 
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Configuration("ttwConfig")
public class Config {

//	private static final Logger LOG = LoggerFactory.getLogger(Config.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 
	 * <label>
	 *		ttw控制流程.
	 * </label>
	 * <p>
	 *		<pre>
	 *			后期如果有需求可以修改此流程（此处采用类责任链模式）
	 *		</pre>
	 * </p>
	 * @Title getProcedureBase
	 * @return ProcedureBase
	 */
	@Bean("ttwProcedure")
	@Scope("singleton")
	public AbstractProcedure getProcedureBase() {
		AbstractProcedure next = applicationContext.getBean("ttwInitBaseBefore", InitBaseBefore.class);
		AbstractProcedure temp = applicationContext.getBean("ttwInitBaseTemplate", InitBaseTemplate.class);
		next.setProcedureBase(temp);
		next = temp;
		temp = applicationContext.getBean("ttwInitBaseData", InitBaseData.class);
		next.setProcedureBase(temp);
		next = temp;
		temp = applicationContext.getBean("ttwInitBaseProduce", InitBaseProduce.class);
		next.setProcedureBase(temp);
		next = temp;
		temp = applicationContext.getBean("ttwInitBaseFilter", InitBaseFilter.class);
		next.setProcedureBase(temp);
		next = temp;
		temp = applicationContext.getBean("ttwInitBaseAfter", InitBaseAfter.class);
		next.setProcedureBase(temp);
		return applicationContext.getBean("ttwInitBaseBefore", InitBaseBefore.class);
	}
	
	@Bean("ttwConfiguration")
	public freemarker.template.Configuration getConfiguration() {
		freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_26);
		configuration.setClassForTemplateLoading(Config.class, "/templates");
		return configuration;
	}

}
