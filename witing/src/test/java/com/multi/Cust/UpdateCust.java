package com.multi.Cust;





import java.sql.Date;

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
		
		CustDTO cust = new CustDTO("ddd", "aaa", "qqq", null, "111-111-111", "qqq@qqq", "qqq", "qqq", 111, null, "qq", 111, "QQQ", null);
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