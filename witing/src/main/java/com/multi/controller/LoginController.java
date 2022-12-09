package com.multi.controller;

import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.service.CityService;
import com.multi.service.CustService;
import com.multi.service.KakaoAPI;
import com.multi.service.NaverAPI;

@Controller
public class LoginController {
	
	String logindir = "login/";
	String regidir = "register/";
	
	@Autowired
	CustService custservice;
	@Autowired
	CityService cityservice;
	
	@Autowired
	private KakaoAPI kakao;
	@Autowired
	private NaverAPI naver;	
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center",logindir+"login");
		return "index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model model, String id, String pwd, HttpSession session) {
		try {
			CustDTO cust = custservice.get(id);
			if(!pwd.equals(cust.getCustpwd())) {
				model.addAttribute("center",logindir+"loginfail");
			}else {
				session.setAttribute("logincust", cust);
				model.addAttribute("center","maincenter");
			}
		} catch (Exception e) {
			model.addAttribute("center",logindir+"loginfail");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/loginfail")
	public String loginfail(Model model) {
		model.addAttribute("center",logindir+"loginfail");
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null)
			session.invalidate();
		return "redirect:/";
	}
	 
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("center",regidir+"register");
		return "index";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model model, String custid, String custpwd, String custname, String birth, String phone, String email, String addr, String addrdetail, Integer zipcode, String gender, String country) {
		CustDTO cust = new CustDTO(custid, custpwd, custname, Date.valueOf(birth),phone,email,addr,addrdetail,zipcode,null,gender,1000,country, null,null);
		try {
			custservice.register(cust);
			model.addAttribute("name",cust.getCustname());
			model.addAttribute("center",regidir+"registerok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	
	@RequestMapping("/kakaologin")
	public String kakaologin(Model model, String code, HttpSession session) {
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, String> userInfo = kakao.getUserInfo(access_Token);
		try {
			CustDTO existcust = custservice.get("k_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")));

			if(existcust == null) {//최초 카카오 로그인시 DBd에 데이터 저장
				CustDTO cust = new CustDTO("k_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), "k_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("name"), Date.valueOf("2022-"+userInfo.get("birthday")), "010-1234-1234", userInfo.get("email"), "", "", 12345, null, userInfo.get("gender"), 1000, "Korea", null,null);
				custservice.register(cust);
				session.setAttribute("logincust", cust);
			}else {//최초 카카오 로그인이 아닐경우 기존 데이터 조회하여 session에 추
				session.setAttribute("logincust", existcust);
			}
			model.addAttribute("citylist",cityservice.getall());
			session.setAttribute("access_Token", access_Token);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return "index";
	}
	
	@RequestMapping("/naverlogin")
	public String naverlogin(Model model, String code, HttpSession session) {
		String access_Token = naver.getAccessToken(code);
		HashMap<String, String> userInfo = naver.getUserInfo(access_Token);

		try {
			CustDTO existcust = custservice.get("n_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")));
			if(existcust == null) {//최초 네이버 로그인시 DB에 데이터 저장
				CustDTO cust = new CustDTO("n_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), "n_"+userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("name"), Date.valueOf(userInfo.get("birth")), "010-1234-1234", userInfo.get("email"), "", "", 12345, null, userInfo.get("gender"), 1000, "Korea", null,null);
				custservice.register(cust);
				session.setAttribute("logincust", cust);
			}else {//최초 네이버 로그인이 아닐경우 기존 데이터 조회하여 session에 추
				session.setAttribute("logincust", existcust);
			}
			model.addAttribute("citylist",cityservice.getall());
			session.setAttribute("access_Token", access_Token);
		}catch(Exception e) {
			
		}
		
		return "index";
	}

}
