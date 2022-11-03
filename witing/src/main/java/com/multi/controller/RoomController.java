package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.dto.RoomDTO;
import com.multi.service.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	RoomService service;


}
