package com.taotao.portal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前段控制器
 * @author lquan
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String indexShow(){
		
		return "index";
	}
}
