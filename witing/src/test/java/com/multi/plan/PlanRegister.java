package com.multi.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.PlanDTO;
import com.multi.service.PlanService;

@SpringBootTest
class PlanRegister {

	@Autowired
	PlanService service;
	
	@Test
	void contextLoads() {
		PlanDTO pl = new PlanDTO(null, 25, "장소", 33.542639f, 126.668572f, 1,
				"할일", "dbb", "제목", "내용", null, 0, 0, 0, null, null, null,null);
		try {
			service.register(pl);
			System.out.println(pl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
