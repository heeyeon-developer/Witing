package com.multi.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.PlanDTO;
import com.multi.service.PlanService;

@SpringBootTest
class GetPlan {

	@Autowired
	PlanService service;
	
	@Test
	void contextLoads() {
		PlanDTO plan = null;
		try {
			plan = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(plan);
	
	}

}
