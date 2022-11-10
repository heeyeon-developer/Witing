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
//			int toppostid = list.get(0).getPostid();
//			PostDTO post = postservice.answercheck(toppostid);
			
			for(int i=0; i<list.size(); i++) {
				if(postservice.answercheck(list.get(i).getPostid()) != null) {
					list.get(i).setAnswer("답변완료");
				}else {
					list.get(i).setAnswer("답변미완료");
				}
			}
//			model.addAttribute("answer", post);
			model.addAttribute("imgpath", "/images/myqnaimg.jpg");
			model.addAttribute("pagename", "Q&A");
			model.addAttribute("cust", cust);
			model.addAttribute("list", list);
			model.addAttribute("mpcenter", "qna");
			model.addAttribute("center", "mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/qnadetail")
	public String qnadetail(Model model, int postid) {
		int toppostid = postid;
		PostDTO post = null;
		PostDTO answer = null;
		try {
			post = postservice.get(postid);
			answer = postservice.selectanswer(toppostid);
			model.addAttribute("imgpath", "/images/qnadetailimg.jpg");
			model.addAttribute("pagename", "My Page");
			model.addAttribute("pagename", "Q&A");
			model.addAttribute("cust", post);  /* mypageindex와 파라미터 맞춰주기 위한 것 */ 
			model.addAttribute("qnadetail", post);
			model.addAttribute("answer", answer);
			model.addAttribute("mpcenter", "qnadetail");
			model.addAttribute("center", "mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/deleteqna")
	public String qnadelete(Model model, int postid, String custid) {
		try {
			postservice.remove(postid);
			CustDTO cust = custservice.get(custid);
			List<PostDTO> list = postservice.myqna(custid);
			model.addAttribute("list", list);
			model.addAttribute("imgpath", "/images/myqnaimg.jpg");
			model.addAttribute("pagename", "Q&A"); 
			model.addAttribute("cust", cust);
			model.addAttribute("mpcenter", "qna");
			model.addAttribute("center", "mypageindex");
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
		System.out.println("postid : "+qna.getPostid());
		System.out.println("custid : "+qna.getCustid());
		System.out.println("title : "+qna.getTitle());
		System.out.println("text : "+qna.getText());
		System.out.println(qna);
		try {
			postservice.register(qna);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:qnamore?hotelid="+qna.getHotelid();
	}
	
	@RequestMapping("/qnamore")
	public String qnamore(Model model, Integer hotelid) {
		List<PostDTO> list = null;
		try {
			list = postservice.hotelqnaall(hotelid);
			model.addAttribute("list", list);
			model.addAttribute("center", "qnamore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/qnamoredetail")
	public String qnamoredetail(Model model, int postid) {
		int toppostid = postid;
		PostDTO post = null;
		PostDTO answer = null;
		
		try {
			post = postservice.get(postid);
			answer = postservice.selectanswer(toppostid);
			System.out.println(post);
			model.addAttribute("qnadetail",post);
			model.addAttribute("answer", answer);
			model.addAttribute("center", "qnamoredetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	

}
