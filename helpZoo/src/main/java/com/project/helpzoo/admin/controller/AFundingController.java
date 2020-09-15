package com.project.helpzoo.admin.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.project.helpzoo.admin.model.service.AFundingService;
import com.project.helpzoo.admin.model.vo.AFunding;

@Controller
@RequestMapping("/admin/funding/*")
public class AFundingController {

	@Autowired
	private AFundingService afundingService;
	
	@RequestMapping("main")
	public ModelAndView fundingSchedule(ModelAndView mv) {
		
		// 제목, 시작 날짜, 종료 날짜 가져오기
		List<AFunding> funding = afundingService.selectFunding();
		
		for(AFunding fun : funding) {
			System.out.println(fun);
		}
		
		System.out.println("con" + funding);
		
		mv.addObject("funding", funding);
		mv.setViewName("admin/funding/adminFSchedule");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="updateAction",
			produces="application/text; charset=utf-8;")
	public String fundingUpdate(AFunding funding) {
		System.out.println(funding);
		
		int result = afundingService.updateFunding(funding);
		
		return result+"";
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAction",
			produces="application/text; charset=utf-8;")
	public String fundingDelete(String fundingNo) {
		System.out.println(fundingNo);
		
		int result = afundingService.deleteFunding(fundingNo);
		
		return result+"";
		
	}
}
