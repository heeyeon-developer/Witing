package com.multi.controller;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.PlanDTO;
import com.multi.service.PlanService;

@Controller
public class PlanController {
	
	@Autowired
	PlanService service;
	
	@RequestMapping("/plan")
	public String main(Model model, int accomid, String title, Date traveltime, Integer cnt, String gender,
			String country, String custid, Date birth, String text, String planname, String planname1,
			String planname2, String todo, String todo1, String todo2) {
		List<PlanDTO> list = null;
		try {
			list = service.plandetail(accomid);
			model.addAttribute("list", list);
			model.addAttribute("center", "plan");
			
			model.addAttribute("title", list.get(0).getTitle());
			model.addAttribute("traveltime", list.get(0).getTraveltime());
			model.addAttribute("cnt", list.get(0).getCnt());
			model.addAttribute("gender", list.get(0).getGender());
			model.addAttribute("country", list.get(0).getCountry());
			model.addAttribute("custid", list.get(0).getCustid());
			model.addAttribute("birth", list.get(0).getBirth());
			model.addAttribute("text", list.get(0).getText());
			
			/*
			model.addAttribute("planname", list.get(0).getPlanname());
			model.addAttribute("planname1", list.get(1).getPlanname());
			model.addAttribute("planname2", list.get(2).getPlanname());
			model.addAttribute("todo", list.get(0).getTodo());
			model.addAttribute("todo1", list.get(1).getTodo());*/
//			for(PlanDTO i : list)
//				System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/getmarker2")
	public Object getmarker2(String loc) {
		//지역정보를 받게되면 지역에따라 마커가 생성된다~
		JSONArray ja = new JSONArray();
		
		if(loc.equals("s")) {
			JSONObject jo1 = new JSONObject();
			jo1.put("title", "국밥집");
			jo1.put("target", "http://www.naver.com");
			jo1.put("lat", 37.5286891);
			jo1.put("lng", 127.061432);
			ja.add(jo1);
			
			JSONObject jo2 = new JSONObject();
			jo2.put("title", "짬뽕집");
			jo2.put("target", "http://www.daum.net");
			jo2.put("lat", 37.5386891);
			jo2.put("lng", 127.021432);
			ja.add(jo2);
			
			JSONObject jo3 = new JSONObject();
			jo3.put("title", "닭발집");
			jo3.put("target", "http://www.google.com");
			jo3.put("lat", 37.5186891);
			jo3.put("lng", 127.031432);
			ja.add(jo3);
			
		}else if(loc.equals("b")) {
			JSONObject jo1 = new JSONObject();
			jo1.put("title", "국밥집");
			jo1.put("target", "http://www.naver.com");
			jo1.put("lat", 37.5286891);
			jo1.put("lng", 127.061432);
			ja.add(jo1);
			
			JSONObject jo2 = new JSONObject();
			jo2.put("title", "짬뽕집");
			jo2.put("target", "http://www.daum.net");
			jo2.put("lat", 37.5386891);
			jo2.put("lng", 127.021432);
			ja.add(jo2);
			
			JSONObject jo3 = new JSONObject();
			jo3.put("title", "닭발집");
			jo3.put("target", "http://www.google.com");
			jo3.put("lat", 37.5186891);
			jo3.put("lng", 127.031432);
			ja.add(jo3);
			
		}else if(loc.equals("j")) {
			JSONObject jo1 = new JSONObject();
			jo1.put("title", "국밥집");
			jo1.put("target", "http://www.naver.com");
			jo1.put("lat", 37.5286891);
			jo1.put("lng", 127.061432);
			ja.add(jo1);
			
			JSONObject jo2 = new JSONObject();
			jo2.put("title", "짬뽕집");
			jo2.put("target", "http://www.daum.net");
			jo2.put("lat", 37.5386891);
			jo2.put("lng", 127.021432);
			ja.add(jo2);
			
			JSONObject jo3 = new JSONObject();
			jo3.put("title", "닭발집");
			jo3.put("target", "http://www.google.com");
			jo3.put("lat", 37.5186891);
			jo3.put("lng", 127.031432);
			ja.add(jo3);
			
		}

		return ja;
	}

}
