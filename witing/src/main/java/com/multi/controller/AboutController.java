package com.multi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AboutController {

	
	@Value("${admindir}")
	String admindir;
	@Value("${custdir}")
	String custdir;
	
	@GetMapping("/about")
	public String main(Model model) {
		model.addAttribute("about","about");
		return "about";
	}
}