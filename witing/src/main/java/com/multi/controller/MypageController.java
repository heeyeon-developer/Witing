package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@Controller
public class MypageController {
	String dir = "mypage/";
	
	@Autowired
	CustService custservice;
	
	@RequestMapping("/mypage")
	public String mypage(Model model, String custid, String certification) {
		CustDTO cust = null;
		try {
			model.addAttribute("imgpath", "/images/mypageimg.jpg");
			model.addAttribute("pagename", "My Page");
			if(certification == null) {
				cust = custservice.get(custid);
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", dir+"mypage");
				model.addAttribute("center", dir+"mypageindex");
			} else {
				cust = custservice.certifi_update(certification, custid);
				cust = custservice.get(custid);
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", dir+"mypage");
				model.addAttribute("center",dir+"mypageindex");
			}
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
				model.addAttribute("mpcenter", dir+"custdetail");
				model.addAttribute("center", dir+"mypageindex");
				
			
			}else {
				model.addAttribute("cust", cust);
				model.addAttribute("status", "0");
				model.addAttribute("imgpath", "/images/mypageimg.jpg");
				model.addAttribute("pagename", "My Page");
				model.addAttribute("mpcenter", dir+"mypage");
				model.addAttribute("center", dir+"mypageindex");
				
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
			model.addAttribute("mpcenter", dir+"mypage");
			model.addAttribute("center", dir+"mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mypage?custid="+cust.getCustid();
	}
	
	
}
