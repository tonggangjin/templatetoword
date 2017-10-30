package com.agen.ttw.produce;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractProduce;
import com.agen.ttw.model.BasicInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component("ttwOldProduce")
public class OldProduce extends AbstractProduce {

	@Value("${ttw.produce.OldProduce.includes}")
	public void init(String[] includes) {
		TEAMPLATES.addAll(Arrays.asList(includes));
	}
	
	@Autowired
	@Qualifier("ttwConfiguration")
	private Configuration configuration;

	@Override
	public void produce(BasicInfo basicInfo) throws Exception {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		Writer out = null;
		File outFile = basicInfo.getFile();
		outFile.getParentFile().mkdir();
		outFile.createNewFile();
		try {
			fos = new FileOutputStream(outFile);
			osw = new OutputStreamWriter(fos, "UTF-8");
			out = new BufferedWriter(osw);
			Template template = configuration.getTemplate(basicInfo.getTemplateName());
			template.process(basicInfo.getData(), out);
			out.flush();
		} finally {
			close(fos, osw, out);
		}

	}

	private void close(FileOutputStream fos, OutputStreamWriter osw, Writer out) {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
			if (osw != null) {
				osw.close();
				osw = null;
			}
			if (fos != null) {
				fos.close();
				fos = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
