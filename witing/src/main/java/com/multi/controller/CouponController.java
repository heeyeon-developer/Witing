package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CouponDTO;
import com.multi.service.CouponService;

@Controller
public class CouponController {
	
	@Autowired
	CouponService service;
	
	@RequestMapping("/coupon")
	public String coupon(Model model, String custid) {
		
		try {
			List<CouponDTO> list = service.getcustcoupon(custid);
			model.addAttribute("custid",custid);
			model.addAttribute("list",list);
			model.addAttribute("center","coupon");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
