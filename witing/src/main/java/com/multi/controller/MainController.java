package com.multi.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
			model.addAttribute("imgpath", "/images/mypageimg.jpg");
			model.addAttribute("pagename", "My Page");
			model.addAttribute("cust", cust);
			model.addAttribute("mpcenter", "mypage");
			model.addAttribute("center","mypageindex");
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
				model.addAttribute("imgpath", "/images/custdetailimg.jpg");
				model.addAttribute("pagename", "Infomation");
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", "custdetail");
				model.addAttribute("center","mypageindex");
				
			
			}else {
				model.addAttribute("cust", cust);
				model.addAttribute("status", "0");
				model.addAttribute("imgpath", "/images/mypageimg.jpg");
				model.addAttribute("pagename", "My Page");
				model.addAttribute("mpcenter", "mypage");
				model.addAttribute("center","mypageindex");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(Model model, CustDTO cust) {
		try {
			custservice.modify(cust);
			model.addAttribute("imgpath", "/images/mypageimg.jpg");
			model.addAttribute("pagename", "My Page");
			model.addAttribute("cust", cust);
			model.addAttribute("mpcenter", "mypage");
			model.addAttribute("center","mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mypage?custid="+cust.getCustid();
	}
}
