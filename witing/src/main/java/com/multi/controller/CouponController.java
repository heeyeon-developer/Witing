package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CouponDTO;
import com.multi.dto.CustDTO;
import com.multi.service.CouponService;
import com.multi.service.CustService;

@Controller
public class CouponController {
	
	@Autowired
	CouponService service;
	@Autowired
	CustService custservice;
	
	@RequestMapping("/coupon")
	public String coupon(Model model, String custid) {
		try {
			CustDTO cust = custservice.get(custid);
			List<CouponDTO> list = service.getcustcoupon(custid);
			model.addAttribute("imgpath", "images/hotel/gyeongju2.jpg");
			model.addAttribute("pagename", "Coupon");
			model.addAttribute("cust", cust);
			model.addAttribute("custid",custid);
			model.addAttribute("list",list);
			model.addAttribute("mpcenter", "coupon");
			model.addAttribute("center","mypageindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
