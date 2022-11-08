package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CityDTO;
import com.multi.dto.CustDTO;
import com.multi.dto.PostDTO;
import com.multi.dto.RoomDTO;
import com.multi.service.CustService;
import com.multi.service.HotelService;
import com.multi.service.PostService;
import com.multi.service.RoomService;

@Controller
public class QnAController {
	@Autowired
	CustService custservice;
	@Autowired
	PostService postservice;
	@Autowired
	HotelService hotelservice;
	@Autowired
	RoomService roomservice;
	
	
	
	@RequestMapping("/qna")
	public String qna(Model model, String custid) {
		System.out.println(custid);
		try {
			CustDTO cust = custservice.get(custid);
			List<PostDTO> list = postservice.myqna(custid);
			System.out.println(list);
			model.addAttribute("qna", cust);
			model.addAttribute("list", list);
			model.addAttribute("center", "qna");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/qnadetail")
	public String qnadetail(Model model, int postid) {
		try {
			PostDTO post = postservice.get(postid);
			model.addAttribute("qnadetail", post);
			model.addAttribute("center", "qnadetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/writeqna")
	public String writeqna(Model model,Integer hotelid,Integer roomid,String hotelname,
			String roomimg1,String roomimg2,String roomimg3,String roomimg4,String hotelimg1,
			String roomtype1,String roomtype2) {
		List<RoomDTO> list = null;
		List<CityDTO> city = null;
		try {
			list = roomservice.roomall(hotelid);
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
			model.addAttribute("center", "writeqna");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/qnasendimpl")
	public String qnasendimpl(Model model, PostDTO qna) {
		
		System.out.println(qna.getTitle());
		System.out.println(qna.getCustid());
		System.out.println(qna.getText());
		System.out.println(qna.getHotelid());
		try {
			postservice.register(qna);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	

}
