package com.multi.plan;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.PlanDTO;
import com.multi.service.PlanService;

@SpringBootTest
class GetPlanXY {

	@Autowired
	PlanService service;
	
	@Test
	void contextLoads() {
		List<PlanDTO> list = null;
		try {
			list = service.xy(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(PlanDTO p:list) {
			System.out.println(p);
		}
	}

}




