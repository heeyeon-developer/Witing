package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CityDTO;
import com.multi.dto.CustDTO;
import com.multi.dto.PostDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.OCRUtil;
import com.multi.frame.Util;
import com.multi.service.CustService;
import com.multi.service.HotelService;
import com.multi.service.PostService;
import com.multi.service.RoomService;


@Controller
public class ReviewController {
	String reviewdir = "review/";
	String mypagedir = "mypage/";
	
	@Autowired
	CustService custservice;
	@Autowired
	PostService postservice;
	@Autowired
	HotelService hotelservice;
	@Autowired
	RoomService roomservice;
	
	@Value("${admindir}")
	String admindir;
	@Value("${custdir}")
	String custdir;
	
	@RequestMapping("/review")
	public String review(Model model, String custid) {
		CustDTO cust = null;
		List<PostDTO> list = null;
		try {
			cust = custservice.get(custid);
			list = postservice.myreview(custid);
			model.addAttribute("imgpath", "/images/myqnaimg.jpg");
			model.addAttribute("pagename", "Review");
			model.addAttribute("cust", cust);
			model.addAttribute("list", list);
			model.addAttribute("mpcenter", reviewdir+"review");
			model.addAttribute("center", mypagedir+"mypageindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/reviewdetail")
	public String reviewdetail(Model model, int postid) {
		PostDTO post = null;
		try {
			post = postservice.reviewdetail(postid);
			model.addAttribute("imgpath", "/images/myqnaimg.jpg");
			model.addAttribute("pagename", "Review");
			model.addAttribute("cust", post); /* mypageindex와 파라미터를 맞추기 위함 */
			model.addAttribute("reviewdetail", post);
			model.addAttribute("mpcenter", reviewdir+"reviewdetail");
			model.addAttribute("center", mypagedir+"mypageindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/deletereview")
	public String qnadelete(Model model, int postid, String custid) {
		try {
			postservice.remove(postid);
			CustDTO cust = custservice.get(custid);
			List<PostDTO> list = postservice.myreview(custid);
			model.addAttribute("list", list);
			model.addAttribute("imgpath", "/images/myqnaimg.jpg");
			model.addAttribute("pagename", "Review"); 
			model.addAttribute("cust", cust);
			model.addAttribute("mpcenter", reviewdir+"review");
			model.addAttribute("center", mypagedir+"mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/ocrimpl")
	public String ocrimpl(Model model, PostDTO obj) {
		String img = obj.getImgname().getOriginalFilename();	// 파일덩어리 안에있는 파일이름을 꺼낸다. 
		obj.setImg(img);
		System.out.println("0-0-0-:"+obj.getImg());
		try {
			Util.saveFile(obj.getImgname(), admindir, custdir);		// 이미지 덩어리를 관리자 디렉, 사용자 디렉에 저장 (상단에 경로를 @Value 써줌)
			String result = OCRUtil.getText(img);	// 결과받기
			System.out.println("RESULT: "+result);
			
			
			JSONParser jsonparser = new JSONParser();
			JSONObject jo = (JSONObject)jsonparser.parse(result.toString());
			System.out.println(jo.toString());
			JSONArray ja1 = (JSONArray) jo.get("images");	// images라는 배열을 가져온다.
			JSONObject jo1 = (JSONObject) ja1.get(0); // 배열에서 첫번째 object를 꺼냄
			JSONArray ja2 = (JSONArray) jo1.get("fields"); // jo1에서 fields라는 배열을 꺼냄
			
			JSONObject f1 = (JSONObject) ja2.get(0);	// fields라는 배열에서 첫번째 
			JSONObject f2 = (JSONObject) ja2.get(1);	// fields라는 배열에서 두번째
			JSONObject f3 = (JSONObject) ja2.get(2);	// fields라는 배열에서 세번째 
			
			String name1 = (String) f1.get("inferText");
			String name2 = (String) f2.get("inferText");
			String no = (String) f3.get("inferText");
			
			model.addAttribute("name1",name1);
			model.addAttribute("name2",name2);
			model.addAttribute("no",no);
			
			model.addAttribute("center","ocrresult");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/writereview")
	public String writereview(Model model,Integer hotelid,Integer roomid,String hotelname,
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
			model.addAttribute("center", reviewdir+"writereview");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/reviewsendimpl")
	public String reviewsendimpl(Model model, PostDTO review) {
		String img = review.getImgname().getOriginalFilename();
		review.setImg(img);
		try {
			Util.saveFile(review.getImgname(), admindir, custdir);
			postservice.reviewinsert(review);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:reviewmore?hotelid="+review.getHotelid();
	}
	
	@RequestMapping("/reviewmore")
	public String reviewmore(Model model, Integer hotelid) {
		List<PostDTO> list = null;
		try {
			list = postservice.hotelreviewall(hotelid);
			
			model.addAttribute("list", list);
			model.addAttribute("center", reviewdir+"reviewmore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/reviewmoredetail")
	public String reviewmoredetail(Model model, int postid) {
		PostDTO post = null;
		try {
			post = postservice.reviewdetail(postid);
			model.addAttribute("reviewdetail",post);
			model.addAttribute("center", reviewdir+"reviewmoredetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
}
