package com.multi.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		try {
			CustDTO cust = custservice.get(custid);
			model.addAttribute("mypage", cust);
			model.addAttribute("center","mypage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custdetail")
	public String custdetail(Model model, String custid, String custpwd) {
		System.out.println(custpwd);
		try {
			CustDTO cust = custservice.get(custid);
			if(custpwd.equals(cust.getCustpwd())) {
				model.addAttribute("custdetail", cust);
				model.addAttribute("center","custdetail");
			
			}else {
				model.addAttribute("status", "0");
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
			System.out.println("2: "+ cust);
			custservice.modify(cust);
			System.out.println("2: "+ cust);
			model.addAttribute("mypage", cust);
			model.addAttribute("center","mypage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
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
	
	@RequestMapping("/naverlogin")
	public String naverlogin(Model model, HttpSession session, String custid, String custpwd, String custname, String phone, String email, String gender, String country) {
		CustDTO cust = new CustDTO(custid.substring(0,20), custpwd.substring(0,20), custname, Date.valueOf("2022-11-11"),phone,email,"","",0,null,gender.equals("F")?"woman":"man",1000,country);
		try {
			CustDTO check = custservice.get(custid.substring(0,20));
			if(check == null)//기존에 가입하지 않은 네이버 회원일 경우 데이터 저장
				custservice.register(cust);
			session.setAttribute("logincust", cust);
			model.addAttribute("name",cust.getCustname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
}
