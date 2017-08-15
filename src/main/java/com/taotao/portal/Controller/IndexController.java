package com.taotao.portal.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AD1Node;

/**
 * 前段控制器
 * @author lquan
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	// 默认设置大广告位的默认信息
	@Value("${AD1Node_Cid}")
	private Long AD1Node_Cid;
	// 大广告位的宽
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	// 大广告位的高
	@Value("${AD1_HEIGH}")
	private Integer AD1_HEIGH;
	@Value("${AD1_HEIGH_B}")
	private Integer AD1_HEIGH_B;
	
	@RequestMapping("/index")
	public String indexShow(Model model){
		// 根据cid 查询轮播图列表
		List<TbContent> list = this.contentService.getContentByCid(this.AD1Node_Cid);
		// 将数据转换成AD1node类型数据
		List<AD1Node> listad = new ArrayList<>();
		for(TbContent content:list){
			AD1Node ad = new AD1Node();
			ad.setHeight(AD1_HEIGH);
			ad.setHeightB(AD1_HEIGH_B);
			ad.setWidth(AD1_WIDTH);
			ad.setWidthB(AD1_WIDTH_B);
			ad.setAlt(content.getTitle());
			ad.setSrc(content.getPic());
			ad.setSrcB(content.getPic2());
			ad.setHref(content.getUrl());
			// 添加到节点列表中
			listad.add(ad);
		}
		
		// 将数据装换成JSon数据
		String ad1json = JsonUtils.objectToJson(listad);
		System.out.println(ad1json);
		// 将数据传递到页面
		model.addAttribute("ad1", ad1json);
		return "index";
	}
}
