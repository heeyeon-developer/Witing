package com.multi.Cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@SpringBootTest
class CustGetAll {

	@Autowired
	CustService service;
	
	@Test
	void contextLoads() {
		try {
			List<CustDTO> list = service.getall();
			for(CustDTO cust : list)
				System.out.println(cust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
