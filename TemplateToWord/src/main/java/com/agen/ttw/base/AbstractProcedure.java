package com.agen.ttw.base;

import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		流程控制.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName AbstractProcedure
 * @author tgj  
 * @date 2017年10月30日 下午1:16:59 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
public abstract class AbstractProcedure {
	
	/**
	 * 下一个处理器
	 */
	protected AbstractProcedure procedure;

	public void setProcedureBase(AbstractProcedure procedure) {
		this.procedure = procedure;
	}

	/**
	 * 
	 * <label>
	 *		核心处理方法.
	 * </label>
	 * <p>
	 *		<pre>
	 *			
	 *		</pre>
	 * </p>
	 * @Title process
	 * @param basicInfo
	 * @return
	 * @throws Exception boolean
	 */
	public abstract boolean process(BasicInfo basicInfo) throws Exception;
}
