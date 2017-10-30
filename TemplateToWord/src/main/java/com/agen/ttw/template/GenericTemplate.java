package com.agen.ttw.template;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractTemplate;
import com.agen.ttw.model.BasicInfo;

import javassist.NotFoundException;

/**
 * 
 * <label>
 *		通用模版选择器.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName GenericTemplate
 * @author tgj  
 * @date 2017年10月30日 下午1:24:28 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwGenericTemplate")
public class GenericTemplate extends AbstractTemplate {
	
	private static String basePath;
	
	private static final String[] TYPES = {".docx", ".xml", ".ftl"};
	
	@PostConstruct
	public void init() {
		GenericTemplate.basePath = GenericTemplate.class.getResource("/templates/").getPath();
	}

	@Override
	public String initTemplate(BasicInfo basicInfo) throws Exception {
		String[] partPath = {basicInfo.getProductId().toString(), basicInfo.getInstitutionId().toString()};
		String result = findTemplate(basePath, partPath, partPath.length, TYPES, 0);
		if (StringUtils.isNotBlank(result)) return result;
		throw new NotFoundException("not fond template, BasicInfo:" + basicInfo.toString());
	}
	
	private String findTemplate(String basePath, String[] partPath, int len, String[] types, int index) {
		if (len == -1) return null;
		Path path = Paths.get(basePath, Arrays.stream(Arrays.copyOf(partPath, len)).collect(Collectors.joining("_")) + types[index]);
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) return path.getFileName().toString();
		index++;
		if (index == types.length) {
			len--;
			index = 0;
		}
		return findTemplate(basePath, partPath, len, types, index);
	}
	
}
