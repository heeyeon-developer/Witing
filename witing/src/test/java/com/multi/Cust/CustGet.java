package com.multi.Cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@SpringBootTest
class CustGet {

	@Autowired
	CustService service;
	
	@Test
	void contextLoads() {
		try {
			CustDTO cust = service.get("hyk");
			System.out.println(cust);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
