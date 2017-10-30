package com.agen.ttw.base;

import java.util.HashSet;
import java.util.Set;

import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		过滤器.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName AbstractFilter
 * @author tgj  
 * @date 2017年10月30日 下午1:15:36 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
public abstract class AbstractFilter {
	
	/**
	 * 按模版过滤是否由此处理
	 */
	protected final Set<String> TEAMPLATES = new HashSet<>();
	
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
	 * @Title initData
	 * @param basicInfo
	 * @throws Exception void
	 */
	public abstract void filter(BasicInfo basicInfo) throws Exception;
	
	/**
	 * 
	 * <label>
	 *		默认验证方法.
	 * </label>
	 * <p>
	 *		<pre>
	 *			
	 *		</pre>
	 * </p>
	 * @Title validate
	 * @param str
	 * @return boolean
	 */
	public boolean validate(String str) {
		if (TEAMPLATES.isEmpty()) return false;
		return TEAMPLATES.contains(str);
	}
}
