package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CustDTO;
import com.multi.dto.PostDTO;
import com.multi.service.CustService;
import com.multi.service.PostService;

@Controller
public class QnAController {
	@Autowired
	CustService custservice;
	@Autowired
	PostService postservice;
	
	@RequestMapping("/qna")
	public String main(Model model, String custid) {
		System.out.println(custid);
		try {
			CustDTO cust = custservice.get(custid);
			List<PostDTO> list = postservice.myqna(custid);
			System.out.println(list);
			model.addAttribute("qna", cust);
			model.addAttribute("myqna", list);
			model.addAttribute("center", "qna");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

}
