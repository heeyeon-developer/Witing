package com.multi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.PlanDTO;
import com.multi.dto.PostDTO;
import com.multi.dto.ReplyDTO;
import com.multi.service.CustService;
import com.multi.service.PlanService;
import com.multi.service.ReplyService;

@Controller

public class PlanController {
	
	String dir = "accompany/";
	
	@Autowired
	PlanService service;
	
	@Autowired
	CustService cust_service;
	
	@Autowired
	ReplyService reply_service;
	
	@RequestMapping("/plan")
	public String main(Model model, int accomid, String title, Date traveltime, Integer cnt, String gender,
			String country, String custid, Date birth, String accomtext, String planname, String todo, Integer planid,
			Integer replyid) {
		List<PlanDTO> list = null;
		List<PlanDTO> xy = null;
		List<ReplyDTO> reply = null;
		List<CustDTO> cust = null;
		try {
			list = service.plandetail(accomid);
			xy = service.xy(accomid);
			reply = reply_service.accomreply(accomid);
			cust = cust_service.custget(custid);
			model.addAttribute("cust", cust);
			
			model.addAttribute("reply", reply);
			model.addAttribute("xy", xy);
			model.addAttribute("list", list);
			model.addAttribute("center", dir+"plan");
			
			model.addAttribute("title", list.get(0).getTitle());
			model.addAttribute("traveltime", list.get(0).getTraveltime());
			model.addAttribute("cnt", list.get(0).getCnt());
			model.addAttribute("gender", list.get(0).getGender());
			model.addAttribute("country", list.get(0).getCountry());
			model.addAttribute("custid", list.get(0).getCustid());
			model.addAttribute("birth", list.get(0).getBirth());
			model.addAttribute("accomtext", list.get(0).getAccomtext());
			model.addAttribute("accomid",accomid);
			model.addAttribute("replyid",replyid);
			
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
	
}
