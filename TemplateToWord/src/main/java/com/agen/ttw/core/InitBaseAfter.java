package com.agen.ttw.core;

import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.model.BasicInfo;

/**
 * 
 * <label>
 *		处理完毕后.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName InitBaseAfter
 * @author tgj  
 * @date 2017年10月30日 下午1:22:19 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwInitBaseAfter")
public class InitBaseAfter extends AbstractProcedure {

	@Override
	public boolean process(BasicInfo basicInfo) throws Exception {
		System.out.println("处理完毕");
		return true;
	}

}
