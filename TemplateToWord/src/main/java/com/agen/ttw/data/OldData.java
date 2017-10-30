package com.agen.ttw.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.agen.ttw.base.AbstractData;
import com.agen.ttw.entity.TBg;
import com.agen.ttw.entity.TDiseaseGene;
import com.agen.ttw.entity.TMemberCyb;
import com.agen.ttw.entity.TMemberJcData;
import com.agen.ttw.entity.XxMember;
import com.agen.ttw.model.BasicInfo;
import com.agen.ttw.service.BgService;
import com.agen.ttw.service.DiseaseGeneService;

import javassist.NotFoundException;

@Component("ttwOldData")
public class OldData extends AbstractData {
	
	@Autowired
	@Qualifier("ttwBgService")
	private BgService bgService;
	@Autowired
	@Qualifier("ttwDiseaseGeneService")
	private DiseaseGeneService diseaseGeneService;
	
	@Value("${ttw.data.oldData.includes}")
	public void init(String[] includes) {
		TEAMPLATES.addAll(Arrays.asList(includes));
	}

	@Override
	@Transactional
	public void initData(BasicInfo basicInfo) throws Exception {
		Map<String, Object> initData = basicInfo.getData();
		initData.put("bgId", basicInfo.getBgId());
		/** 组装数据 */
		TBg bg = bgService.findOne(basicInfo.getBgId());
		TMemberCyb cyb = bg.getTMemberCyb();
		initData.put("ordersn", cyb.getCybbm());
		XxMember member = bg.getXxMember();
		initData.put("memberno", member.getMemberno());
		initData.put("productname", bg.getXxProduct().getName());
		initData.put("name", member.getName());
		if (member.getGender() == null) {
			initData.put("sex", "");
		} else {
			initData.put("sex", member.getGender().equals(0) ? "男": "女");
		}
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
		List<TMemberJcData> jcsjs = new ArrayList<TMemberJcData>(bg.getXxOrder().getTMemberJcDatas());
		if(null != jcsjs && jcsjs.size() >= 1) {
			Collections.sort(jcsjs, new Comparator<TMemberJcData>() {
				@Override
				public int compare(TMemberJcData o1, TMemberJcData o2) {
					// TODO Auto-generated method stub
					return o2.getCreateDate().after(o1.getCreateDate()) ? 1 : (o2.getCreateDate().before(o1.getCreateDate()) ? -1 : 0);
				}
			});
		} else {
			throw new NotFoundException("don't find jcsj");
		}
		TMemberJcData tjd = jcsjs.get(0);
		Date jcDate = tjd.getCreateDate();
		initData.put("cjDate", null !=  jcDate? DateFormatUtils.format(jcDate, "yyyy年MM月dd日") : "");
		String jcdata1 = tjd.getJcdata1();
		String jcdata2 = tjd.getJcdata2();
		initData.put("jcdata1", jcdata1);
		initData.put("jcdata2", jcdata2);
		
		TDiseaseGene temp = new TDiseaseGene();
		temp.setXxProduct(bg.getXxProduct());
		List<TDiseaseGene> gene = diseaseGeneService.findAll(Example.of(temp));
		for (TDiseaseGene dg : gene) {
			if (dg.getBh() == 1) {
				if (jcdata1.equals(dg.getGenevalue1())) {
					initData.put("jctype1", dg.getGenevalue1type());
					initData.put("genetimes1", dg.getGenetimes1());
					
				}
				if (jcdata1.equals(dg.getGenevalue2())) {
					initData.put("jctype1", dg.getGenevalue2type());
					initData.put("genetimes1", dg.getGenetimes2());
				}
				if (jcdata1.equals(dg.getGenevalue3())) {
					initData.put("jctype1", dg.getGenevalue3type());
					initData.put("genetimes1", dg.getGenetimes3());
				}
				
			}
			if (dg.getBh() == 2) {
				if (jcdata2.equals(dg.getGenevalue1())) {
					initData.put("jctype2", dg.getGenevalue1type());
					initData.put("genetimes2", dg.getGenetimes1());
				}
				if (jcdata2.equals(dg.getGenevalue2())) {
					initData.put("jctype2", dg.getGenevalue2type());
					initData.put("genetimes2", dg.getGenetimes2());
				}
				if (jcdata2.equals(dg.getGenevalue3())) {
					initData.put("jctype2", dg.getGenevalue3type());
					initData.put("genetimes2", dg.getGenetimes3());
				}
				
			}
		}
		
		if (bg.getXxAdminByFxsId() != null) {
			initData.put("report_person",  (StringUtils.isNotEmpty(bg.getXxAdminByFxsId().getName())) ? bg.getXxAdminByFxsId().getName() : "");
		} 
	}

}
