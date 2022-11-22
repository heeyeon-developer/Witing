package com.multi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.OrderlistDTO;
import com.multi.dto.PayDTO;
import com.multi.dto.ReservationDTO;
import com.multi.dto.RoomDTO;
import com.multi.service.CustService;
import com.multi.service.HotelService;
import com.multi.service.OrderlistService;
import com.multi.service.PayService;
import com.multi.service.ReservationService;
import com.multi.service.RoomService;

@Controller
public class ReservationController {
	String mypagedir = "mypage/";
	
	String dir = "reservation/";
	
	@Autowired
	RoomService roomservice;
	@Autowired
	CustService custservice;
	@Autowired
	HotelService hotelservice;
	@Autowired
	ReservationService reservationservice;
	@Autowired
	OrderlistService orderlistservice;
	@Autowired
	PayService payservice;

	@RequestMapping("/reservation")
	public String reservation(Model model, String custid, Integer roomid) {
		
		try {
			CustDTO cust = custservice.get(custid);
			RoomDTO room = roomservice.get(roomid);
			HotelDTO hotel = hotelservice.get(room.getHotelid());
			List<Integer> people = new ArrayList<>();
			for(int i=room.getStandard(); i<=room.getMax(); i++) {
				people.add(i);
			}
			model.addAttribute("custid",custid);
			model.addAttribute("roomid",roomid);
			model.addAttribute("custname",cust.getCustname());
			model.addAttribute("roominfo",hotel.getHotelname()+"-"+room.getRoomtype());
			model.addAttribute("totalprice",room.getPrice());
			model.addAttribute("addprice",room.getAddprice());
			model.addAttribute("people",people);
			model.addAttribute("center",dir+"reservation");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/reservationlist")
	public String reservationlist(Model model, String custid) {
		try {
			CustDTO cust = custservice.get(custid);
			List<OrderlistDTO> list = orderlistservice.custorder(custid);
			model.addAttribute("cust", cust);
			model.addAttribute("imgpath", "images/hotel/gyeongju2.jpg");
			model.addAttribute("pagename","Reservation");
			model.addAttribute("list",list);
			model.addAttribute("mpcenter", dir+"reservationlist");
			model.addAttribute("center",mypagedir+"mypageindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/reservimpl")
	public String reservimpl(Model model, OrderlistDTO order) {
		int orderid = 0;
		try {
			orderlistservice.register(order);
			orderid = order.getOrderid();
			payservice.register(new PayDTO(null,orderid,order.getTotalprice(),"결재완료","카드"));
			reservationservice.register(new ReservationDTO(null, order.getRoomid(),order.getCustid(),order.getCnt(),order.getSdate(),order.getEdate(),0,""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:reservok?orderid="+orderid;
	}
	
	@RequestMapping("/reservok")
	public String reserviok(Model model, Integer orderid) {
		try {
			OrderlistDTO order = orderlistservice.get(orderid);
			RoomDTO room = roomservice.get(order.getRoomid());
			HotelDTO hotel = hotelservice.get(room.getHotelid());
			model.addAttribute("roominfo",hotel.getHotelname()+"-"+room.getRoomtype());
			model.addAttribute("name",custservice.get(order.getCustid()).getCustname());
			model.addAttribute("order", order);
			model.addAttribute("center",dir+"reservationdetail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/reservdetail")
	public String reservdetail(Model model, Integer orderid) {
		try {
			OrderlistDTO order = orderlistservice.get(orderid);
			RoomDTO room = roomservice.get(order.getRoomid());
			HotelDTO hotel = hotelservice.get(room.getHotelid());
			model.addAttribute("roominfo",hotel.getHotelname()+"-"+room.getRoomtype());
			model.addAttribute("name",custservice.get(order.getCustid()).getCustname());
			model.addAttribute("order", order);
			model.addAttribute("center",dir+"reservationdetail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
