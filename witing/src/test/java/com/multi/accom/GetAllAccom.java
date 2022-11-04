package com.multi.accom;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.CityDTO;
import com.multi.service.AccompanyService;

@SpringBootTest
class GetAllAccom {

	@Autowired
	AccompanyService service;
	
	
	@Test
	void contextLoads() {
		List<AccompanyDTO> list = null;
		try {
			list = service.getall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(AccompanyDTO a:list) {
			System.out.println(a);
		}
		
	}

}




