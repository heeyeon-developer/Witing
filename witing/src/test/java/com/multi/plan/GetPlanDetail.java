package com.multi.plan;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.PlanDTO;
import com.multi.service.PlanService;

@SpringBootTest
class GetPlanDetail {

	@Autowired
	PlanService service;
	
	@Test
	void contextLoads() {
		List<PlanDTO> list = null;
		try {
			list = service.plandetail(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(PlanDTO p:list) {
			System.out.println(p);
		}
	}

}




