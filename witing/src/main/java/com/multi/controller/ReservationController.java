package com.multi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.ReservationDTO;
import com.multi.dto.RoomDTO;
import com.multi.service.CustService;
import com.multi.service.HotelService;
import com.multi.service.ReservationService;
import com.multi.service.RoomService;

@Controller
public class ReservationController {
	
	@Autowired
	RoomService roomservice;
	@Autowired
	CustService custservice;
	@Autowired
	HotelService hotelservice;
	@Autowired
	ReservationService reservationservice;

	@RequestMapping("/reservation")
	public String reservation(Model model, String custid, Integer roomid) {
		
		try {
			CustDTO cust = custservice.get(custid);
			RoomDTO room = roomservice.get(roomid);
			HotelDTO hotel = hotelservice.get(room.getHotelid());
			model.addAttribute("custname",cust.getCustname());
			model.addAttribute("roominfo",hotel.getHotelname()+"-"+room.getRoomtype());
			model.addAttribute("center","reservation");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/reservationlist")
	public String reservationlist(Model model, String custid) {
		try {
			List<ReservationDTO> list = reservationservice.custreserv(custid);
			model.addAttribute("list",list);
			model.addAttribute("center","reservationlist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
