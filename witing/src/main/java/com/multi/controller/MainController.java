package com.multi.controller;

import java.sql.Date;
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
	
	@RequestMapping("/")
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
	public String mypage(Model model, String custid) {
		CustDTO cust = null;
		try {
			cust = custservice.get(custid);
			model.addAttribute("mypage", cust);
			model.addAttribute("center","mypage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custdetail")
	public String custdetail(Model model, String custid, String custpwd) {
		try {
			CustDTO cust = custservice.get(custid);
			if(custpwd.equals(cust.getCustpwd())) {
				model.addAttribute("custdetail", cust);
				model.addAttribute("center","custdetail");
			}else {
				model.addAttribute("status", "0");
				model.addAttribute("mypage", cust);
				model.addAttribute("center", "mypage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(Model model, CustDTO cust) {
		try {
			System.out.println("1: "+ cust);
			custservice.modify(cust);
			System.out.println("2: "+ cust);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mypage?custid="+cust.getCustid();
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
		
		return "redirect:";
	}
	
	@RequestMapping("/loginfail")
	public String loginfail(Model model) {
		model.addAttribute("center","loginfail");
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null)
			session.invalidate();
		return "redirect:";
	}
	 
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("center","register");
		return "index";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model model, String custid, String custpwd, String custname, String birth, String phone, String email, String addr, String addrdetail, Integer zipcode, String gender, String country) {
		CustDTO cust = new CustDTO(custid, custpwd, custname, Date.valueOf(birth),phone,email,addr,addrdetail,zipcode,null,gender,1000,country);
		try {
			custservice.register(cust);
			model.addAttribute("name",cust.getCustname());
			model.addAttribute("center","registerok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
}
