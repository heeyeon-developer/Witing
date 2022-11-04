package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.dto.CustDTO;
import com.multi.dto.LikeDTO;
import com.multi.service.CustService;
import com.multi.service.LikeService;

@RestController
public class AjaxController {

	@Autowired
	CustService custservice;
	
	@Autowired
	LikeService like_service;
	
	@RequestMapping("/idcheck")
	public Object idcheck(String id) {
		boolean result = true;
		try {
			List<CustDTO> list = custservice.getall();
			for(CustDTO cust : list) {
				if(id.equals(cust.getCustid())) {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/addlike")
	public Object addlike(String custid, int hotelid) {
		LikeDTO like = new LikeDTO(0,hotelid,custid,0,"","","","","",0,"");
		try {
			like_service.register(like);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelid;
	}
}
