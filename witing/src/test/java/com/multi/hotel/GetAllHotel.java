package com.multi.hotel;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.HotelDTO;
import com.multi.service.HotelService;

@SpringBootTest
class GetAllHotel {

	@Autowired
	HotelService service;
	
	
	@Test
	void contextLoads() {
		List<HotelDTO> list = null;
		try {
			list = service.getall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(HotelDTO h:list) {
			System.out.println(h);
		}
		
	}

}




