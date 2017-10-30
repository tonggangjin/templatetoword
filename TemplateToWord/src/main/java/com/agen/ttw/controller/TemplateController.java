package com.agen.ttw.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agen.ttw.base.AbstractProcedure;
import com.agen.ttw.model.BasicInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <label>
 *		基于http方法生成报告（演示）.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName TemplateController
 * @author tgj  
 * @date 2017年10月30日 下午1:21:20 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Api(value = "生成报告")
@RestController("ttwTemplateController")
public class TemplateController {
	
	@Autowired
	@Qualifier("ttwProcedure")
	private AbstractProcedure abstractProcedure;

	@ApiOperation(value="生成报告", notes="根据BgId来生成报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bgId", value = "报告ID (exmaple:123456)", required = true, dataType = "String", paramType = "query", example = "123456"),
    })
	@RequestMapping(value = "/carete", method = RequestMethod.POST)
	public String carete(String bgId) throws Exception {
		try {
			System.out.println(deal(BigDecimal.valueOf(Long.valueOf(bgId))));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "生成成功";
	}
	
	@ApiOperation(value="生成报告", notes="根据BgId来生成报告")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "bgIds", value = "报告ID数组  (exmaple:[123,456,789])", required = true, dataType = "Array[String]", paramType = "body", example = "13,456,789"),
	})
	@RequestMapping(value = "/caretes", method = RequestMethod.POST)
	public String caretes(@RequestBody String[] bgIds) throws Exception {
		for (String bgId : bgIds) {
			try {
				System.out.println(deal(BigDecimal.valueOf(Long.valueOf(bgId))));
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return "生成成功";
	}
	
	private boolean deal(BigDecimal bgId) throws Exception {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.setBgId(bgId);
		return abstractProcedure.process(basicInfo);
	}
}
