package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.CateDTO;
import com.multi.dto.CityDTO;
import com.multi.service.CateService;
import com.multi.service.CityService;

@Controller
public class MainController {

	
	@Autowired
	CityService cityservice;
	@Autowired
	CateService cateservice;
	
	@RequestMapping("/")
	public String main(Model model) {
		try {
			List<CityDTO> list = cityservice.getall();
			List<CateDTO> cate = cateservice.getcate();
			model.addAttribute("city",list);
			model.addAttribute("cate",cate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("center","maincenter");
		return "index";
	}
	
}
