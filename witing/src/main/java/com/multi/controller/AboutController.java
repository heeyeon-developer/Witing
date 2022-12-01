package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AboutController {

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("center", "about");
		return "index";
	}
}
