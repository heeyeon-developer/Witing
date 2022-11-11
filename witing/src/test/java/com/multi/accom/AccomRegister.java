package com.multi.accom;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.AccompanyDTO;
import com.multi.service.AccompanyService;

@SpringBootTest
class AccomRegister {

	@Autowired
	AccompanyService service;
	
	@Test
	void contextLoads() {
		AccompanyDTO ac = new AccompanyDTO(0, "dbb", "title", "text", Date.valueOf("2022-11-11"), 3, 36.327470f, 127.426815f);
		try {
			service.register(ac);
			int r = ac.getAccomid();
				System.out.println(r);
			System.out.println(ac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
