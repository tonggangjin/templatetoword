package com.agen.ttw.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.agen.ttw.dao.DiseaseGeneDao;
import com.agen.ttw.entity.TDiseaseGene;


@Service("ttwDiseaseGeneService")
public class DiseaseGeneService extends BaseService<TDiseaseGene> {
	
	@Resource(name = "ttwDiseaseGeneDao")
	private DiseaseGeneDao diseaseGeneDao;
}
