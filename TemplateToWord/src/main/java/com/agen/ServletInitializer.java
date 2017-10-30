package com.agen;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * <label>
 *		使用spring boot项目可以部署在Tomcat里.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName ServletInitializer
 * @author tgj  
 * @date 2017年10月30日 下午1:12:42 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TTWApplication.class);
	}

}
