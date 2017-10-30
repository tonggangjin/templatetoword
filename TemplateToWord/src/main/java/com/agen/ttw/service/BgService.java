package com.agen.ttw.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.agen.ttw.dao.BgDao;
import com.agen.ttw.entity.TBg;


@Service("ttwBgService")
public class BgService extends BaseService<TBg> {
	
	@Resource(name = "ttwBgDao")
	private BgDao bgDao;
}
