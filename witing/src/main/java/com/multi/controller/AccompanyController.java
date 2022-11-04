package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.AccompanyDTO;
import com.multi.service.AccompanyService;

@Controller
public class AccompanyController {
	
	@Autowired
	AccompanyService service;
	
	@RequestMapping("/accompany")
	public String main(Model model) {
		List<AccompanyDTO> list = null;
		try {
			list = service.getall();
			model.addAttribute("list", list);
			model.addAttribute("center", "accompany");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/writeaccom")
	public String writeaccom(Model model, String custid) {
		model.addAttribute("custid", custid);
		model.addAttribute("center", "writeaccom");
		return "index";
	}
	

}
