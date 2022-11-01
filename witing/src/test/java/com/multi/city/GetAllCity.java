package com.multi.city;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CityDTO;
import com.multi.service.CityService;

@SpringBootTest
class GetAllCity {

	@Autowired
	CityService service;
	
	
	@Test
	void contextLoads() {
		List<CityDTO> list = null;
		try {
			list = service.getall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(CityDTO c:list) {
			System.out.println(c);
		}
		
	}

}




