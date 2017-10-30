package com.agen.ttw.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agen.ttw.base.AbstractData;
import com.agen.ttw.entity.TBg;
import com.agen.ttw.entity.TDiseaseGene;
import com.agen.ttw.entity.TMemberCyb;
import com.agen.ttw.entity.TMemberJcData;
import com.agen.ttw.entity.XxMember;
import com.agen.ttw.model.BasicInfo;
import com.agen.ttw.service.DiseaseGeneService;

import javassist.NotFoundException;

@Component("ttwTengPaiData")
public class TengPaiData extends AbstractData {
	
	@Autowired
	@Qualifier("ttwDiseaseGeneService")
	private DiseaseGeneService diseaseGeneService;
	private static final Map<String, Integer> BASIC = new HashMap<>();
	
	private static final String ADMINID = "315";
	
	@PostConstruct
	public void init() {
		BASIC.put("115", 1);
		BASIC.put("116", 3);
		BASIC.put("124", 5);
		BASIC.put("122", 7);
		BASIC.put("119", 9);
		BASIC.put("121", 11);
		TEAMPLATES.addAll(BASIC.keySet().stream().map(item -> item + "_" + ADMINID).collect(Collectors.toSet()));
	}
	
	@Override
	@Transactional
	public void initData(BasicInfo basicInfo) throws Exception {
		Map<String, Object> initData = basicInfo.getData();
		initData.put("bgId", basicInfo.getBgId());
		/** 组装数据 */
		TBg bg = (TBg) initData.get("bg");
		TMemberCyb cyb = bg.getTMemberCyb();
		initData.put("ordersn", cyb.getCybbm());
		XxMember member = bg.getXxMember();
		initData.put("memberno", member.getMemberno());
		initData.put("name", member.getName());
		if(null != member.getBirth()) {
			Calendar calerdar = Calendar.getInstance();
			calerdar.setTime(member.getBirth());
			int year = Calendar.getInstance().get(Calendar.YEAR) - calerdar.get(Calendar.YEAR);
			initData.put("age", year + "");
		} else {
			initData.put("age", "");
		}
		initData.put("hzjgname",null != member.getXxAdmin().getName() ? member.getXxAdmin().getName() : "");			
		initData.put("fxsname",null != bg.getXxAdminByFxsId() ? bg.getXxAdminByFxsId().getName() : "");
		initData.put("bgDate", null != bg.getBgDate() ? DateFormatUtils.format(bg.getBgDate(), "yyyy年MM月dd日") : DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		initData.put("sjDate", null != cyb.getSjDate() ? DateFormatUtils.format(cyb.getSjDate(), "yyyy年MM月dd日") : "");
		if (member.getGender() == null) {
			initData.put("sex", "");
		} else {
			initData.put("sex", member.getGender().equals(0) ? "男": "女");
		}
		
		Set<TBg> sets = cyb.getTBgs();
		for (TBg tbg : sets) {
			if(!BASIC.containsKey(tbg.getXxProduct().getId().toString())) continue;
			TMemberJcData tjd = tbg.getTMemberJcData();
			if(null == tjd) throw new NotFoundException("检测数据不全，无法生成");
			String jcdata1 = tjd.getJcdata1();
			String jcdata2 = tjd.getJcdata2();
			Integer base = BASIC.get(tbg.getXxProduct().getId().toString());
			initData.put("jcdata" + base, jcdata1);
			initData.put("jcdata" + (base + 1), jcdata2);
			
			TDiseaseGene temp = new TDiseaseGene();
			temp.setXxProduct(bg.getXxProduct());
			List<TDiseaseGene> gene = diseaseGeneService.findAll(Example.of(temp));
			for (TDiseaseGene dg : gene) {
				if (dg.getBh() == 1) {
					if (jcdata1.equals(dg.getGenevalue1())) {
						initData.put("jctype" + base, dg.getGenevalue1type());
						initData.put("genetimes" + base, dg.getGenetimes1());
						
					}
					if (jcdata1.equals(dg.getGenevalue2())) {
						initData.put("jctype"  + base, dg.getGenevalue2type());
						initData.put("genetimes" + base, dg.getGenetimes2());
					}
					if (jcdata1.equals(dg.getGenevalue3())) {
						initData.put("jctype" + base, dg.getGenevalue3type());
						initData.put("genetimes" + base, dg.getGenetimes3());
					}
					
				}
				if (dg.getBh() == 2) {
					if (jcdata2.equals(dg.getGenevalue1())) {
						initData.put("jctype" + (base + 1), dg.getGenevalue1type());
						initData.put("genetimes" + (base + 1), dg.getGenetimes1());
					}
					if (jcdata2.equals(dg.getGenevalue2())) {
						initData.put("jctype" + (base + 1), dg.getGenevalue2type());
						initData.put("genetimes" + (base + 1), dg.getGenetimes2());
					}
					if (jcdata2.equals(dg.getGenevalue3())) {
						initData.put("jctype" + (base + 1), dg.getGenevalue3type());
						initData.put("genetimes" + (base + 1), dg.getGenetimes3());
					}
					
				}
			}
		}
		
		if (bg.getXxAdminByFxsId() != null) {
			initData.put("report_person",  (StringUtils.isNotEmpty(bg.getXxAdminByFxsId().getName())) ? bg.getXxAdminByFxsId().getName() : "");
		}
	}

}
