package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@Controller
public class MainController {

	@Autowired
	CustService custservice;
	
	@RequestMapping("/main")
	public String main(Model model) {
		model.addAttribute("center","maincenter");
		return "index";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model) {
		CustDTO cust = null;
		try {
			cust = custservice.get("kmj");
			model.addAttribute("mypage", cust);
			model.addAttribute("center","mypage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/custdetail")
	public String custdetail(Model model) {
		CustDTO cust = null;
		try {
			cust = custservice.get("kmj");
			model.addAttribute("custdetail", cust);
			model.addAttribute("center","custdetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	
	
}
