package com.multi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.CustDTO;
import com.multi.dto.PlanDTO;
import com.multi.dto.ReplyDTO;
import com.multi.service.AccompanyService;
import com.multi.service.CustService;
import com.multi.service.PlanService;
import com.multi.service.ReplyService;

@Controller
public class AccompanyController {
	
	@Autowired
	AccompanyService service;
	
	@Autowired
	PlanService plan_service;
	
	@Autowired
	CustService cust_service;
	
	@Autowired
	ReplyService reply_service;
	
	@RequestMapping("/accompany")
	public String main(Model model) {
		List<AccompanyDTO> list = null;
		try {
			list = service.getall();
			model.addAttribute("list", list);
			model.addAttribute("center", "accompany");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/deleteaccom")
	public String deleteplan(Model model, Integer accomid) {
		try {
			List<Integer> deplan = new ArrayList<>();
			for(PlanDTO a : plan_service.plandetail(accomid)) {
				deplan.add(a.getPlanid());
			}
			for(Integer i : deplan)
				plan_service.remove(i);
			service.remove(accomid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:accompany";
	}
	
	@RequestMapping("/writeaccom")
	public String writeaccom(Model model, String custid) {
		model.addAttribute("custid", custid);
		model.addAttribute("center", "writeaccom");
		return "index";
	}
	
	@RequestMapping("/writereply")
	public String writereply(Model model, String custid, int accomid) {
		model.addAttribute("accomid", accomid);
		model.addAttribute("custid", custid);
		return "index";
	}
	
	@RequestMapping("/replyimpl")
	public String replyimpl(Model model, String custid, int accomid, String comment) {
		ReplyDTO reply = new ReplyDTO(0, accomid, custid, comment, null, 0);
		try {
			reply_service.register(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:plan?accomid="+reply.getAccomid();
	}
	
	
	@RequestMapping("/updatereply")
	public String updatereply(Model model, ReplyDTO reply,int replyid, int accomid, String comment) {
		try {
			reply_service.modify(reply);
			System.out.println(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:plan?accomid="+accomid;
	}

	@RequestMapping("/delreply")
	public String delreply(Model model, int replyid, int accomid) {
		ReplyDTO reply = new ReplyDTO();
		try {
			System.out.println(replyid);
			System.out.println(accomid);
			reply_service.remove(replyid);
			model.addAttribute("reply", reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:plan?accomid="+accomid;
		
	}
	
	@RequestMapping("/accomimpl")
	public String accomimpl(Model model, String custid,String title,int cnt,String traveltime, int idx,String planname,String todo,String accomtext,
			float planx, float plany) {
		Integer accomid = 0;
		try {
			List<CustDTO> clist = cust_service.custget(custid);
			System.out.println(traveltime);
			AccompanyDTO ac = new AccompanyDTO(0, custid, title, accomtext, Date.valueOf(traveltime), cnt, 0.0f, 0.0f);
			service.register(ac);
			accomid = ac.getAccomid();
			System.out.println(accomid);
			for(CustDTO c : clist) {
				PlanDTO pl = new PlanDTO(0, ac.getAccomid(), planname, planx, plany, 0, todo,
						custid, title, accomtext, Date.valueOf(traveltime), cnt,
						ac.getLocationx(), ac.getLocationy(), c.getCountry(), c.getGender(), c.getBirth());
				System.out.println(c);
				plan_service.register(pl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:plan?accomid="+accomid;
	}
	

}
