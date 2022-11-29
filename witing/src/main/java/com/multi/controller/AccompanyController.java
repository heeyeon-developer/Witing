package com.multi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.Criteria;
import com.multi.dto.CustDTO;
import com.multi.dto.PageDTO;
import com.multi.dto.PlanDTO;
import com.multi.dto.ReplyDTO;
import com.multi.service.AccompanyService;
import com.multi.service.CustService;
import com.multi.service.PlanService;
import com.multi.service.ReplyService;

@Controller

public class AccompanyController {
	
	String dir = "accompany/";
	
	@Autowired
	AccompanyService service;
	
	@Autowired
	PlanService plan_service;
	
	@Autowired
	CustService cust_service;
	
	@Autowired
	ReplyService reply_service;
	
	@RequestMapping("/accompany")
	public String main(Model model, Criteria cri) {
		List<AccompanyDTO> list = null;
		try {
			list = service.accompage(cri);
			model.addAttribute("list", list);
			model.addAttribute("center", dir+"accompany");
			
			int total = service.accomcnt(cri);
			PageDTO pageMaker = new PageDTO(total, cri);
			model.addAttribute("pageMaker", pageMaker);
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
		model.addAttribute("center", dir+"writeaccom");
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
	
	
//	@RequestMapping("/updatereply")
//	public String updatereply(Model model, ReplyDTO reply) {
//		try {
//			reply_service.modify(reply);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:accompany";
//	}
	
	@RequestMapping("/updatereply")
	public String updatereply(Model model, Integer replyid, Integer accomid, String comment, String custid) {
		ReplyDTO reply = new ReplyDTO(replyid, accomid, custid, comment, null, 0);
		try {
			System.out.println(replyid);
			System.out.println(accomid);
			System.out.println(comment);
			reply_service.modify(reply);
			model.addAttribute("reply", reply);
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
	public String accomimpl(Model model, String custid,String title,int cnt,String traveltime, String accomtext,
			int[] idx, String[] roadname, String[] planname, String[] todo, float[] planx, float[] plany) {
		int accomid = 0;
		try {
			CustDTO clist = cust_service.get(custid);
			AccompanyDTO ac = new AccompanyDTO(0, custid, title, accomtext, Date.valueOf(traveltime), cnt, 0.0f, 0.0f);
			service.register(ac);
			accomid = ac.getAccomid();
			System.out.println(accomid);
			for(int i=0; i<idx.length; i++) {
				PlanDTO pl = new PlanDTO(0, ac.getAccomid(), planname[i], planx[i], plany[i], idx[i], todo[i],
						"", "", "", Date.valueOf(traveltime), 0, 0.0f, 0.0f, "", "",Date.valueOf(traveltime), "","");
				plan_service.register(pl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:plan?accomid="+accomid;
	}
	
	
//	String[] planname = request.getParameterValues("planname");
//	String[] todo = request.getParameterValues("todo");
	
//	float[] planx = Float.parseFloat(request.getParameterValues("plnax"));
//	float[] plany = Float.parseFloat(request.getParameterValues("plnay"));
	

}
