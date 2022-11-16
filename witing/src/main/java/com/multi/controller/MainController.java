package com.multi.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.dto.CityDTO;
import com.multi.dto.CustDTO;
import com.multi.service.CityService;
import com.multi.service.CustService;
//import com.multi.service.MailSendService;

@Controller
public class MainController {

	@Autowired
	MailSender sender;
	@Autowired
	CustService custservice;
	@Autowired
	CityService cityservice;
//	@Autowired
//	private MailSendService mailService;
	
	@RequestMapping("/")
	public String main(Model model) {
		try {
			List<CityDTO> list = cityservice.getall();
			model.addAttribute("citylist",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("center","maincenter");
		return "index";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model, String custid, String certification) {
		CustDTO cust = null;
		try {
			model.addAttribute("imgpath", "/images/mypageimg.jpg");
			model.addAttribute("pagename", "My Page");
			if(certification == null) {
				cust = custservice.get(custid);
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", "mypage");
				model.addAttribute("center","mypageindex");
			} else {
				cust = custservice.certifi_update(certification, custid);
				cust = custservice.get(custid);
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", "mypage");
				model.addAttribute("center","mypageindex");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custdetail")
	public String custdetail(Model model, String custid, String custpwd) {
		try {
			CustDTO cust = custservice.get(custid);
			if(custpwd.equals(cust.getCustpwd())) {
				model.addAttribute("imgpath", "/images/custdetailimg.jpg");
				model.addAttribute("pagename", "Infomation");
				model.addAttribute("cust", cust);
				model.addAttribute("mpcenter", "custdetail");
				model.addAttribute("center","mypageindex");
				
			
			}else {
				model.addAttribute("cust", cust);
				model.addAttribute("status", "0");
				model.addAttribute("imgpath", "/images/mypageimg.jpg");
				model.addAttribute("pagename", "My Page");
				model.addAttribute("mpcenter", "mypage");
				model.addAttribute("center","mypageindex");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(Model model, CustDTO cust) {
		try {
			custservice.modify(cust);
			model.addAttribute("imgpath", "/images/mypageimg.jpg");
			model.addAttribute("pagename", "My Page");
			model.addAttribute("cust", cust);
			model.addAttribute("mpcenter", "mypage");
			model.addAttribute("center","mypageindex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mypage?custid="+cust.getCustid();
	}
	
	@RequestMapping("/CheckMail") // AJAX와 URL을 매핑시켜줌 
	@ResponseBody  //AJAX후 값을 리턴하기위해 작성
	public String SendMail(String mail) {
			Random random = new Random();  //난수 생성을 위한 랜덤 클래스
			String key="";  //인증번호 

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mail); //스크립트에서 보낸 메일을 받을 사용자 이메일 주소 
			
			//입력 키를 위한 코드
			for(int i = 0; i < 3; i++) {
				int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
				key+=(char)index;
			}
			int numIndex=random.nextInt(8999)+1000; //4자리 랜덤 정수를 생성
			key+=numIndex;
			System.out.println("1");
			message.setSubject("Witing 본인인증을 위한 인증번호 메일");
			System.out.println("2");
			message.setText("인증 번호 : "+key);
			System.out.println("3");
			sender.send(message);
			System.out.println("4");
			System.out.println("key : " + key);
			System.out.println("5");
	       
			return key;
		}

}
