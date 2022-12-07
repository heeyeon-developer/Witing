package com.multi.Cust;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CustDTO;
import com.multi.service.CustService;

@SpringBootTest
class RegisterCust {

	@Autowired
	CustService service;
	
	@Test
	void contextLoads() {
		CustDTO cust = new CustDTO("hytest3", "hy123", "희연테스트3", Date.valueOf("1994-11-11"), "010-1234-1234", "rudals0627@gmail.com", "제주시", "1층", 12345, null, "woman", 0, "Korea",null,null);
		try {
			service.register(cust);
			List<CustDTO> list = service.getall();
			for(CustDTO c : list)
				System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}