package com.multi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.dto.CityDTO;
import com.multi.dto.CustDTO;
import com.multi.dto.LikeDTO;
import com.multi.dto.PlanDTO;
import com.multi.service.CityService;
import com.multi.service.CustService;
import com.multi.service.LikeService;
import com.multi.service.PlanService;

@RestController
public class AjaxController {

	@Autowired
	CustService custservice;
	@Autowired
	LikeService like_service;
	@Autowired
	CityService city_service;
	@Autowired
	PlanService plan_service;
	
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
		int[] result = {hotelid,0};
		int likeid = 0;
		try {
			//고객의 likelist불러오기
			List<LikeDTO> list = like_service.likehotel(custid);
			boolean check = false;
			
			for(LikeDTO h : list) {
				if(hotelid == h.getHotelid()) {
					check = true;
					likeid = h.getLikeid();//삭제시 필요한 고객의 likeid 가져오기
					break;
				}
			}
			if(check) {//이미 like에 담겨있다면 삭제
				like_service.remove(likeid);
				result[1] = -1;
			}else {//담겨있지 않다면 추가
				like_service.register(like);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/likehearts")
	public Object likehearts(String custid) {
		List<Integer> likelist = new ArrayList<>();
		try {
			if(custid != null) {
				List<LikeDTO> likes = like_service.likehotel(custid);
				for(LikeDTO like : likes){
					if(!likelist.contains(like.getHotelid()))
						likelist.add(like.getHotelid());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return likelist;
	}
	
	@RequestMapping("/map")
	public Object map(Model model, int accomid) {
		List<PlanDTO> data = null;
		try {
			data = plan_service.xy(accomid);
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
