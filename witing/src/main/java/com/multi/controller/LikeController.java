package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.LikeDTO;
import com.multi.service.CustService;
import com.multi.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	LikeService service;
	
	@Autowired
	CustService custservice;
	
	@RequestMapping("/like")
	public String main(Model model, String custid) {
		List<LikeDTO> list = null;
		try {
			CustDTO cust = custservice.get(custid);
			list = service.likehotel(custid);
			model.addAttribute("like", cust);
			model.addAttribute("list", list);
			model.addAttribute("center", "like");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/deletelike")
	public String deletelike(Model model, int like_id, String cust_id) {
		try {
			service.remove(like_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:like?custid="+cust_id;
	}
	
	
	
	

}
