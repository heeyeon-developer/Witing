package com.multi.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.service.PlanService;

@SpringBootTest
class DeletePlan {
	@Autowired
	PlanService service;

	@Test
	void contextLoads() {
		try {
			service.remove(16);
			System.out.println("DELETED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
