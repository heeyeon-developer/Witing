package com.multi.accom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.service.AccompanyService;
import com.multi.service.PlanService;

@SpringBootTest
class DeleteAccom {
	@Autowired
	AccompanyService service;

	@Test
	void contextLoads() {
		try {
			service.remove(34);
			System.out.println("DELETED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
