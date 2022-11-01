package com.multi.Cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@SpringBootTest
class UpdateCust {

	@Autowired
	CustService service;
	
	@Test
	void contextLoads() {
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Update OK");
	}

}
