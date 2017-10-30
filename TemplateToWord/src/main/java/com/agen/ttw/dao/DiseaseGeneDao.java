package com.agen.ttw.dao;

import org.springframework.stereotype.Repository;

import com.agen.ttw.entity.TDiseaseGene;

@Repository("ttwDiseaseGeneDao")
public interface DiseaseGeneDao extends BaseDao<TDiseaseGene> {
	
}
