package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CateDTO;
import com.multi.dto.CityDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.RoomDTO;
import com.multi.mapper.CateMapper;
import com.multi.mapper.HotelMapper;
import com.multi.service.CityService;
import com.multi.service.HotelService;
import com.multi.service.RoomService;

@Controller
public class HotelController {
	
	@Autowired
	HotelService service;
	
	@Autowired
	RoomService room_service;
	
	@Autowired
	CityService city_service;
	
	@Autowired
	HotelMapper mapper;
	
	@Autowired
	CateMapper cate_service;
	
	
	@RequestMapping("/hotel")
	public String main(Model model) {
		List<HotelDTO> list = null;
		List<CateDTO> cate = null;
		List<CityDTO> city = null;
		try {
			list = service.hotelcity();
			cate = cate_service.getcate();
			city = city_service.getall();
			model.addAttribute("city", city);
			model.addAttribute("list", list);
			model.addAttribute("cate", cate);
			model.addAttribute("center", "hotel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/cityshotel")
	public String cityshotel(Model model, Integer cityid) {
		try {
			List<HotelDTO> list = service.cityshotel(cityid);
			model.addAttribute("list", list);
			model.addAttribute("center", "hotel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	 
	@RequestMapping("/searchimpl")
	public String searchimpl(Model model, Integer cityid, Integer cateid) {
		List<HotelDTO> search = null;
		List<CateDTO> cate = null;
		List<CityDTO> city = null;
		try {
			search = service.searchhotel(cityid, cateid);
			cate = cate_service.getcate();
			city = city_service.getall();
			model.addAttribute("city", city);
			model.addAttribute("cate", cate);
			model.addAttribute("search", search);
			model.addAttribute("center", "searchimpl");
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
		try {
			list = room_service.roomall(hotelid);
			city = city_service.getall();
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
			model.addAttribute("center", "room");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}

}
