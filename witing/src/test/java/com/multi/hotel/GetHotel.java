package com.multi.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.HotelDTO;
import com.multi.service.HotelService;

@SpringBootTest
class GetHotel {

	@Autowired
	HotelService service;
	
	@Test
	void contextLoads() {
		HotelDTO hotel = null;
		try {
			hotel = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(hotel);
	
	}

}
