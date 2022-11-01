package com.multi.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CityDTO;
import com.multi.service.CityService;

@SpringBootTest
class GetCity {

	@Autowired
	CityService service;
	
	@Test
	void contextLoads() {
		CityDTO city = null;
		try {
			city = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(city);
	
	}

}
