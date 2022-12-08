package com.multi.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CateDTO;
import com.multi.dto.CityDTO;
import com.multi.dto.CriteriaHotel;
import com.multi.dto.HotelDTO;
import com.multi.dto.PageDTO;
import com.multi.dto.PageHotelDTO;
import com.multi.dto.PostDTO;
import com.multi.dto.RoomDTO;
import com.multi.mapper.CateMapper;
import com.multi.mapper.HotelMapper;
import com.multi.service.CateService;
import com.multi.service.CityService;
import com.multi.service.HotelService;
import com.multi.service.PostService;
import com.multi.service.RoomService;

@Controller

public class HotelController {
	
	String dir = "hotel/";
	
	@Autowired
	HotelService service;
	
	@Autowired
	RoomService room_service;
	
	@Autowired
	CityService city_service;
	
	@Autowired
	HotelMapper mapper;
	
	@Autowired
	CateService cate_service;
	
	@Autowired
	PostService post_service;
	
	
	@RequestMapping("/hotel")
	public String main(Model model, CriteriaHotel crihotel) {
		List<HotelDTO> list = null;
		List<CateDTO> cate = null;
		List<CityDTO> city = null;
		try {
			list = service.hotelpage(crihotel);
			cate = cate_service.getall();
			city = city_service.getall();
			
			int total = service.hotelcnt(crihotel);
			PageHotelDTO pageMaker = new PageHotelDTO(total, crihotel);
			model.addAttribute("pageMaker", pageMaker);
			
			model.addAttribute("city", city);
			model.addAttribute("list", list);
			model.addAttribute("cate", cate);
			model.addAttribute("center", dir+"hotel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/cityshotel")
	public String cityshotel(Model model, Integer cityid, CriteriaHotel crihotel) {
		try {
			List<HotelDTO> list = service.cityshotel(cityid);
			
			int total = service.cityshotelcnt(cityid, crihotel);
			PageHotelDTO pageMaker = new PageHotelDTO(total, crihotel);
			model.addAttribute("pageMaker", pageMaker);
			
			model.addAttribute("list", list);
			model.addAttribute("center", dir+"hotel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	 
	@RequestMapping("/searchimpl")
	public String searchimpl(Model model, CriteriaHotel crihotel, Integer cityid, Integer cateid, String sdate, String edate) {
		List<HotelDTO> search = null;
		List<CateDTO> cate = null;
		List<CityDTO> city = null;
		try {
			search = service.searchhotel(cityid, cateid , Date.valueOf(sdate), Date.valueOf(edate));
			cate = cate_service.getall();
			city = city_service.getall();
			
			int total = service.searchhotelcnt(crihotel, cityid, cateid, Date.valueOf(sdate), Date.valueOf(edate));
			PageHotelDTO pageMaker = new PageHotelDTO(total, crihotel);
			model.addAttribute("pageMaker", pageMaker);
			
			model.addAttribute("sdate", sdate);
			model.addAttribute("edate", edate);
			model.addAttribute("cityid", cityid);
			model.addAttribute("cateid", cateid);
			model.addAttribute("city", city);
			model.addAttribute("cate", cate);
			model.addAttribute("search", search);
			model.addAttribute("center", dir+"searchimpl");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	
	@RequestMapping("/room")
	public String room(Model model,int hotelid,Integer roomid,String hotelname,
			String roomimg1,String roomimg2,String roomimg3,String roomimg4,String hotelimg1,
			String roomtype1,String roomtype2) {
		List<RoomDTO> list = null;
		List<CityDTO> city = null;
		List<PostDTO> post = null;
		List<PostDTO> post2 = null;
		try {
			list = room_service.roomall(hotelid);
			city = city_service.getall();
			post = post_service.qnalist5(hotelid);
			post2 = post_service.reviewlist5(hotelid);
			model.addAttribute("citylist", city);
			model.addAttribute("hotelname", list.get(0).getHotelname());
			model.addAttribute("hotelimg1", list.get(0).getHotelimg1());
			model.addAttribute("roomimg1", list.get(0).getRoomimg1());
			model.addAttribute("roomimg2", list.get(0).getRoomimg2());
			model.addAttribute("roomimg3", list.get(1).getRoomimg1());
			model.addAttribute("roomimg4", list.get(1).getRoomimg2());
			model.addAttribute("roomtype1", list.get(0).getRoomtype());
			model.addAttribute("roomtype2", list.get(1).getRoomtype());
			model.addAttribute("roomid", roomid);
			model.addAttribute("list", list);
			model.addAttribute("qnalist5", post);
			model.addAttribute("reviewlist5", post2);
			model.addAttribute("center", dir+"room");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}

}
