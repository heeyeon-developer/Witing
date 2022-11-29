package com.multi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.dto.CityDTO;
import com.multi.dto.Criteria;
import com.multi.dto.CustDTO;
import com.multi.dto.HotelDTO;
import com.multi.dto.OrderlistDTO;
import com.multi.dto.PageDTO;
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
	public String review(Model model, String custid, Criteria cri) {
		CustDTO cust = null;
		List<PostDTO> list = null;
		try {
			cust = custservice.get(custid);
//			list = postservice.myreview(custid);
			list = postservice.myreviewpage(cri);
			int total = postservice.myreviewcnt(cri);
			PageDTO pageMaker = new PageDTO(total, cri);
			model.addAttribute("pageMaker", pageMaker);
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
	@RequestMapping("reviewocr")
	public String reviewocr(Model model,Integer hotelid,Integer roomid,String hotelname,
			String roomimg1,String roomimg2,String roomimg3,String roomimg4,String hotelimg1,
			String roomtype1,String roomtype2) {
		HotelDTO hotel = null;
		List<RoomDTO> list = null;
		List<CityDTO> city = null;
		try {
			hotel = hotelservice.get(hotelid);
			list = roomservice.roomall(hotelid);
			model.addAttribute("hotel", hotel);
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
			model.addAttribute("center", reviewdir+"reviewocr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/ocrimpl")
	public String ocrimpl(Model model, PostDTO obj, HttpSession session, int hotelid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String img = obj.getImgname().getOriginalFilename();	// 파일덩어리 안에있는 파일이름을 꺼낸다. 
		obj.setImg(img);
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
			model.addAttribute("roomid", list.get(0).getRoomid());
			model.addAttribute("list", list);
			model.addAttribute("center", reviewdir+"reviewocr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			list = roomservice.roomall(hotelid);
			model.addAttribute("list", list);
			Util.saveFile(obj.getImgname(), admindir, custdir);		// 이미지 덩어리를 관리자 디렉, 사용자 디렉에 저장 (상단에 경로를 @Value 써줌)
			String result = OCRUtil.getText(img);	// 결과받기
			System.out.println("RESULT: "+result);
			
			JSONParser jsonparser = new JSONParser();
			JSONObject jo = (JSONObject)jsonparser.parse(result.toString());
			// System.out.println(jo.toString());
			JSONArray ja1 = (JSONArray) jo.get("images");	// images라는 배열을 가져온다.
			JSONObject jo1 = (JSONObject) ja1.get(0); // 배열에서 첫번째 object를 꺼냄
			JSONArray ja2 = (JSONArray) jo1.get("fields"); // jo1에서 fields라는 배열을 꺼냄
			
			JSONObject f1 = (JSONObject) ja2.get(0);	// fields라는 배열에서 첫번째 
			JSONObject f2 = (JSONObject) ja2.get(1);	// fields라는 배열에서 두번째
			JSONObject f3 = (JSONObject) ja2.get(2);	// fields라는 배열에서 세번째 
			JSONObject f4 = (JSONObject) ja2.get(3);	// fields라는 배열에서 네번째 
			JSONObject f5 = (JSONObject) ja2.get(4);	// fields라는 배열에서 디섯번째 
			JSONObject f6 = (JSONObject) ja2.get(5);	// fields라는 배열에서 여섯번째 
			
			String orderid = (String) f1.get("inferText");
			String custname = (String) f2.get("inferText");
			String hotel = (String) f3.get("inferText");
			String totalprice = (String) f4.get("inferText");
			String cnt = (String) f5.get("inferText");
			String date = (String) f6.get("inferText");
			
			// hotelname , roomtype 분리
			String replacehotel = hotel.replace(" ", "");
			int idx1 = replacehotel.indexOf("-");
			String hotelname = replacehotel.substring(0, idx1);
			String roomtype = replacehotel.substring(idx1 + 1);
			
			// sdate, edate 분리
			String replacedate = date.replace(" ", "");
			int idx2 = replacedate.indexOf("~");
			String sdate = replacedate.substring(0, idx2);
			String edate = replacedate.substring(idx2 + 1);
			
			int intOrderid = Integer.parseInt(orderid);			// ocr orderid 형변환
			int intTotalprice = Integer.parseInt(totalprice);	// ocr totalprice 형변환
			int intCnt = Integer.parseInt(cnt);					// ocr cnt 형변환
			
			// ocr로 추출한 데이터
			System.out.println("-------------------ocr 데이터-------------");
			System.out.println(custname);
			System.out.println(hotelname);
			System.out.println(roomtype);
			System.out.println(totalprice);
			System.out.println(cnt);
			System.out.println(date);
			System.out.println(replacedate);
			System.out.println(sdate);
			System.out.println(edate);
			
			Date currenttime = new Date();
			String today = format.format(currenttime);
			System.out.println("today : " +today);
			System.out.println("checkin : " +sdate);
			int compare = edate.compareTo(today);
			System.out.println("비교 : "+compare);
			if(compare <= 0) {
				
			}else {
				
			}
			
			CustDTO cust = (CustDTO) session.getAttribute("logincust");
			System.out.println("cust : "+cust);
			String name = cust.getCustname();	// session의 custname
			System.out.println("session의 custname : "+ name);
			
			PostDTO orderdetail = postservice.reviewocr(intOrderid);	// orderlist 데이터 조회
			
			String od_custname = orderdetail.getCustname();
			String od_hotelname = (orderdetail.getHotelname()).replace(" ", "");
			String od_roomtype = orderdetail.getRoomtype();
			int od_totalprice = orderdetail.getTotalprice();
			int od_cnt = orderdetail.getCnt();
			String od_sdate = (String)format.format(orderdetail.getSdate());
			String od_edate = (String)format.format(orderdetail.getEdate());
			int od_hotelid = orderdetail.getOcrhotelid();
			System.out.println("넘어온 것"+hotelid);
			System.out.println("첨부한 것"+od_hotelid);
			System.out.println("--------------------------조회한 데이터--------------------");
			System.out.println(od_custname);
			System.out.println(od_hotelname);
			System.out.println(od_roomtype);
			System.out.println(od_totalprice);
			System.out.println(od_cnt);
			System.out.println(od_sdate);
			System.out.println(od_edate);

//			model.addAttribute("orderid", intOrderid);	
//			model.addAttribute("custname", custname);
//			model.addAttribute("hotelname", hotelname);
//			model.addAttribute("totalprice", intTotalprice);
//			model.addAttribute("cnt", cnt);
//			model.addAttribute("date", date);
			
			
			if(name.equals(od_custname)) {
				System.out.println("이름 일치");
			}
			if(hotelname.equals(od_hotelname)) {
				System.out.println("호텔이름 일치");
			}
			if(roomtype.equals(od_roomtype)) {
				System.out.println("룸타입 일치");
			}
			if(intTotalprice == od_totalprice) {
				System.out.println("가격일치");
			}
			if(intCnt == od_cnt) {
				System.out.println("인원일치");
			}
			if(sdate.equals(od_sdate)) {
				System.out.println("체크인일치");
			}
			if(edate.equals(od_edate)) {
				System.out.println("체크아웃일치");
			}
			
			String dir = "review/";
			
			if(hotelid == od_hotelid) {
				if(name.equals(od_custname) && hotelname.equals(od_hotelname) && roomtype.equals(od_roomtype) 
						&& intTotalprice == od_totalprice && intCnt == od_cnt && sdate.equals(od_sdate) && edate.equals(od_edate)) {
					if(compare <= 0) {	// 
						System.out.println("일치합니다.");
						model.addAttribute("status", "1");
						model.addAttribute("center", dir+"reviewocr");
	//					 "redirect:writereview?hotelid="+hotelid;
					}else {	// 체크아웃 전 일 경우
						model.addAttribute("status", "2");
						model.addAttribute("center", dir+"reviewocr");
					}
				}else {	// 호텔은 일치하지만 다른 세부사항이 일치하지 않는 경우
					System.out.println("일치하지않습니다.");
					model.addAttribute("status", "0");
					model.addAttribute("center", dir+"reviewocr");
//					return "redirect:reviewmore?hotelid="+hotelid; 
				}
			}else {	// 호텔 자체가 다른 호텔 일 경우
				System.out.println("일치하지않습니다.");
				model.addAttribute("status", "0");
				model.addAttribute("center", dir+"reviewocr");
//				return "redirect:reviewmore?hotelid="+hotelid;
			}
		}catch (Exception e) {
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
	public String reviewmore(Model model, Integer hotelid, Criteria cri) {
		List<PostDTO> list = null;
		try {
			list = postservice.reviewmorepage(cri);
			int total = postservice.reviewmorecnt(cri);
			PageDTO pageMaker = new PageDTO(total, cri);
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("hotelid", hotelid);
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
