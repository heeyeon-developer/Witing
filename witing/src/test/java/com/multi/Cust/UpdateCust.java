package com.multi.Cust;

import java.text.SimpleDateFormat;
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
		
		CustDTO cust = new CustDTO("kmj", "kmj123", "전경민", null, "010-1234-1234", "rudals0627@gmail.com", "경기도 광명시", "1층", 12345, null, "man", 0, "Korea");
		try {
			service.modify(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Update OK");
	}

}
// test
// test2