package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CityDTO;
import com.multi.dto.CustDTO;
import com.multi.service.CityService;
import com.multi.service.CustService;

@Controller
public class MainController {

	@Autowired
	CustService custservice;
	@Autowired
	CityService cityservice;
	
	@RequestMapping("/main")
	public String main(Model model) {
		try {
			List<CityDTO> list = cityservice.getall();
			model.addAttribute("citylist",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(Model model, CustDTO cust) {
		try {
			custservice.modify(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mypage";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center","login");
		return "index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model model, String id, String pwd, HttpSession session) {
		try {
			CustDTO cust = custservice.get(id);
			if(!pwd.equals(cust.getCustpwd())) {
				model.addAttribute("center","loginfail");
			}else {
				session.setAttribute("logincust", cust);
				model.addAttribute("center","maincenter");
			}
		} catch (Exception e) {
			model.addAttribute("center","loginfail");
		}
		
		return "index";
	}
	
	@RequestMapping("/loginfail")
	public String loginfail(Model model) {
		model.addAttribute("center","loginfail");
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("center","register");
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null)
			session.invalidate();
		return "index";
	}
	
}
