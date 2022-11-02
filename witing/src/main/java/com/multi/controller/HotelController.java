package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CityDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.RoomDTO;
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
	
	
	@RequestMapping("/hotel")
	public String main(Model model, String cityname) {
		List<HotelDTO> list = null;
		List<CityDTO> city = null;
		try {
			list = service.getall();
			city = city_service.getall();
			model.addAttribute("list", list);
			model.addAttribute("city", city);
			model.addAttribute("center", "hotel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/searchimpl")
	public String searchimpl(Model model, int hotelid) {
		List<HotelDTO> list = null;
		try {
			list = mapper.searchhotel(hotelid);
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", "hotel");
		return "index";
	}

	
	@RequestMapping("/room")
	public String room(Model model, int hotelid, Integer roomid) {
		List<RoomDTO> list = null;
		try {
			list = room_service.roomall(hotelid);
			model.addAttribute("roomid", roomid);
			model.addAttribute("list", list);
			model.addAttribute("center", "room");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	

}
