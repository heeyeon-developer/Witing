package com.multi.accom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.AccompanyDTO;
import com.multi.service.AccompanyService;

@SpringBootTest
class GetAccom {

	@Autowired
	AccompanyService service;
	
	@Test
	void contextLoads() {
		AccompanyDTO accom = null;
		try {
			accom = service.get(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(accom);
	
	}

}
