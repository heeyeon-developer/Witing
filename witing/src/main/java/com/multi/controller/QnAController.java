package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.PostDTO;
import com.multi.service.CustService;
import com.multi.service.HotelService;
import com.multi.service.PostService;

@Controller
public class QnAController {
	@Autowired
	CustService custservice;
	@Autowired
	PostService postservice;
	@Autowired
	HotelService hotelservice;
	
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
	
	@RequestMapping("/write_qna")
	public String write_qna(Model model, Integer hotelid) {

		try {
			HotelDTO hotel = hotelservice.get(hotelid);
			model.addAttribute("hotel", hotel);
			model.addAttribute("center", "write_qna");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

}
