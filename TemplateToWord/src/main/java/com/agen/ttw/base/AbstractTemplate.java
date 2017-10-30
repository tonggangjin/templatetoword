package com.agen.ttw.base;

import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		模版选择基类.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName AbstractTemplate
 * @author tgj  
 * @date 2017年10月30日 下午1:19:11 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
public abstract class AbstractTemplate {
	
	public abstract String initTemplate(BasicInfo basicInfo) throws Exception;
	
}
