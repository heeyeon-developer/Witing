package com.multi.accom;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.Criteria;
import com.multi.service.AccompanyService;

@SpringBootTest
class GetSearchaccom {

	@Autowired
	AccompanyService service;
	
	@Test
	void contextLoads() {
		
		try {
			Criteria cri = new Criteria();
			List<AccompanyDTO> list = service.searchaccom(cri, Date.valueOf("2022-11-17"), Date.valueOf("2022-11-25"));
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
