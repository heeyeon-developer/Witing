package com.multi.Cust;





import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.service.CustService;

@SpringBootTest
class Certifi_update {

	@Autowired
	CustService service;
	
	@Test
	void contextLoads() {
		try {
			service.certifi_update("1", "kmj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Update OK");
	}

}
// test
// test2