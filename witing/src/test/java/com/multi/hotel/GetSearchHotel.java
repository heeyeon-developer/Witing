package com.multi.hotel;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.HotelDTO;
import com.multi.service.HotelService;

@SpringBootTest
class GetSearchHotel {

	@Autowired
	HotelService service;
	
	@Test
	void contextLoads() {
		List<HotelDTO> list = null;
		try {
			list = service.searchhotel(1, 101, Date.valueOf("2022-11-24"), Date.valueOf("2022-11-25"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(HotelDTO h:list) {
			System.out.println(h);
		}
	
	}

}
