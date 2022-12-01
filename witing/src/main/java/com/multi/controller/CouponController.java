package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CouponDTO;
import com.multi.dto.Criteria;
import com.multi.dto.CustDTO;
import com.multi.dto.PageDTO;
import com.multi.service.CouponService;
import com.multi.service.CustService;

@Controller
public class CouponController {
	String mypagedir = "mypage/";
	
	String dir = "coupon/";
	
	@Autowired
	CouponService service;
	@Autowired
	CustService custservice;
	
	@RequestMapping("/coupon")
	public String coupon(Model model, String custid, Criteria cri) {
		try {
			CustDTO cust = custservice.get(custid);
			List<CouponDTO> list = service.couponpage(cri);
			int total = service.couponcnt(cri);
			PageDTO pageMaker = new PageDTO(total, cri);
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("imgpath", "images/hotel/gyeongju2.jpg");
			model.addAttribute("pagename", "Coupon");
			model.addAttribute("cust", cust);
			model.addAttribute("custid",custid);
			model.addAttribute("list",list);
			model.addAttribute("mpcenter", dir+"coupon");
			model.addAttribute("center",mypagedir+"mypageindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
